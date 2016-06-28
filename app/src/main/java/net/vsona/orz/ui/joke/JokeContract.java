package net.vsona.orz.ui.joke;

import net.vsona.baselibrary.presenter.BasePresenter;
import net.vsona.baselibrary.view.BaseView;
import net.vsona.orz.domain.ContentlistEntity;

import java.util.List;

/**
 * Created by roy on 16/6/26.
 */
public interface JokeContract {

    interface View extends BaseView<Presenter> {
        void showError();

        void showData(List<ContentlistEntity> mContentlistEntities);
    }

    interface Presenter extends BasePresenter{
        void load();

    }
}
