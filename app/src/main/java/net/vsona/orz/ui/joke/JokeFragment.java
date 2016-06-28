package net.vsona.orz.ui.joke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.vsona.baselibrary.listener.OnRecyclerViewLastItemVisiableListener;
import net.vsona.baselibrary.utils.BaseUtils;
import net.vsona.orz.R;
import net.vsona.orz.domain.ContentlistEntity;
import net.vsona.orz.ui.BaseFragment;

import org.greenrobot.eventbus.util.ExceptionToResourceMapping;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by roy on 16/6/28.
 */
public class JokeFragment extends BaseFragment implements JokeContract.View {

    @Bind(R.id.roy_recycle_view)
    RecyclerView mRecyclerView;
    private JokeContract.Presenter mJokePresenter;
    private JokeAdapter mJokeAdapter;
    private LinearLayoutManager lm;

    @Override
    protected int getRootLayoutResource() {
        return R.layout.fragment_joke;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, mRootView);

        //init loadingView
        TextView t = new TextView(mActivity);
        t.setText("loading");

        mJokeAdapter = new JokeAdapter(mActivity, t);
        lm = new LinearLayoutManager(mActivity);

        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mJokeAdapter);
        mRecyclerView.addOnScrollListener(new OnRecyclerViewLastItemVisiableListener() {
            @Override
            public int getItemCount() {
                return mJokeAdapter.getItemCount();
            }

            @Override
            public int findLastVisiableItemPosttion() {
                return lm.findLastVisibleItemPosition();
            }

            @Override
            public void onLastItemVisiable() {
                mJokePresenter.load();
            }
        });
    }

    @Override
    protected void initData() {
        mJokePresenter.start();
    }


    @Override
    public void setPresenter(JokeContract.Presenter presenter) {
        mJokePresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showError() {
        BaseUtils.toastMessage(mActivity, "error");
    }

    @Override
    public void showData(List<ContentlistEntity> data) {
        mJokeAdapter.setDataList(data);
        mJokeAdapter.notifyDataSetChanged();
    }
}
