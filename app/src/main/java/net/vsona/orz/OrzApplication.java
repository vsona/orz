package net.vsona.orz;


import com.squareup.leakcanary.LeakCanary;

import net.vsona.baselibrary.app.BaseApplication;
import net.vsona.baselibrary.utils.BaseUtils;
import net.vsona.orz.utils.AppConstants;


/**
 * Created by roy on 16/6/24.
 */
public class OrzApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        initParams();
        initLeak();
    }

    private void initLeak() {
        LeakCanary.install(this);
    }

    private void initParams() {
        AppConstants.sAppPlatform = BaseUtils.isTablet(this) ? AppConstants.ANDROID_PAD : AppConstants.ANDROID_PHONE;
        AppConstants.sAppVersion = BaseUtils.getAppVersion(this);
        AppConstants.sAppVersionName = BaseUtils.getAppVersionName(this);

        //init channel
        if (!AppConstants.sDebug) {
            AppConstants.sAppChannel = BaseUtils.getChannel(this);
        }
    }

    private AppComponent mAppComponent;
    private void initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
