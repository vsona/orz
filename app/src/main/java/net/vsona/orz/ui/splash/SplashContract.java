package net.vsona.orz.ui.splash;

import net.vsona.baselibrary.presenter.BasePresenter;
import net.vsona.baselibrary.view.BaseView;

/**
 * Created by roy on 16/6/26.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void enterMainActivity();
    }

    interface Presenter extends BasePresenter{

    }
}
