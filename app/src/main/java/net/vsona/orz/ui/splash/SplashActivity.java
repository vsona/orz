package net.vsona.orz.ui.splash;

import android.content.Context;
import android.os.Bundle;

import net.vsona.orz.OrzApplication;
import net.vsona.orz.R;
import net.vsona.orz.ui.BaseAppCompatActivity;
import net.vsona.orz.utils.StartActivityUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseAppCompatActivity implements SplashContract.View {

    @Inject
    SplashPrensenter presenter;
    SplashContract.Presenter mPresenter;
    @Inject
    Context mAppContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initInject();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initInject() {
        // 构建Component并注入
//        getActivityComponent().inject(this);
        DaggerSplashComponent.builder()
                .splashPresenterModule(new SplashPresenterModule(this))
                .appComponent(((OrzApplication) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void enterMainActivity() {
        StartActivityUtils.forMainActivity(mActivity);
        finish();
    }


    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
