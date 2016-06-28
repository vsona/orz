package net.vsona.orz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import net.vsona.orz.ActivityScope;


@ActivityScope
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected BaseAppCompatActivity mActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
    //  建议写在基类Activity里
//    protected SplashComponent getActivityComponent(){
//        return  DaggerActivityComponent.builder()
//                .splashActivityModule(new SplashPresenterModule(this))
//                .appComponent(getAppComponent())
//                .build();
//    }

    //  建议写在基类Activity里
//    protected ActivityModule getActivityModule(){
//        return new ActivityModule(this);
//    }

    // 建议写在MyApplication类里
//    public AppComponent getAppComponent(){
//        return DaggerAppComponent.builder()
//                .appModule(new AppModule((OrzApplication) getApplicationContext()))
//                .build();
//    }
}
