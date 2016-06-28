package net.vsona.orz.ui.joke;

import com.orhanobut.logger.Logger;

import net.vsona.orz.api.Api;
import net.vsona.orz.domain.ContentlistEntity;
import net.vsona.orz.domain.JokeEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.trinea.android.common.util.ListUtils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by roy on 16/6/28.
 */
public class JokePresenter implements JokeContract.Presenter {

    private final JokeContract.View mView;
    private int mNextPage = 1;
    private List<ContentlistEntity> mContentlistEntities = new ArrayList<>();

    @Inject
    public JokePresenter(JokeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        System.out.println("JokePresenter.JokePresenter");
    }

    @Override
    public void start() {
        load();
    }

    @Override
    public void load() {
        loadList(mNextPage);
    }

    public void loadList(final int page) {
        System.out.println("JokePresenter.loadList");
        Api.getService()
                .getJoke(page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<JokeEntity, List<ContentlistEntity>>() {
                    @Override
                    public List<ContentlistEntity> call(JokeEntity jokeEntity) {
                        return jokeEntity.getShowapi_res_body().getContentlist();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ContentlistEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mNextPage = page;
                        Logger.e(e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<ContentlistEntity> contentlistEntities) {
                        if (page == 1) {
                            refresh(contentlistEntities);
                        } else {
                            loadMore(contentlistEntities);
                        }
                        mNextPage = (page + 1);
                    }
                });

    }

    private void refresh(List<ContentlistEntity> contentlistEntities) {
        if (ListUtils.isEmpty(contentlistEntities)) {
            return;
        }
        mContentlistEntities = contentlistEntities;
        mView.showData(mContentlistEntities);
    }

    private void loadMore(List<ContentlistEntity> contentlistEntities) {
        if (ListUtils.isEmpty(contentlistEntities)) {
            return;
        }
        mContentlistEntities.addAll(contentlistEntities);
        mView.showData(mContentlistEntities);
    }
}
