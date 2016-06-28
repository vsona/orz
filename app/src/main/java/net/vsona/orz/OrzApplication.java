package net.vsona.orz;


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
    }

    private void initParams() {
        AppConstants.APP_PLATFORM = BaseUtils.isTablet(this) ? AppConstants.ANDROID_PAD : AppConstants.ANDROID_PHONE;
        AppConstants.APP_VERSION = BaseUtils.getAppVersion(this);
        AppConstants.APP_VERSION_NAME = BaseUtils.getAppVersionName(this);

        //init channel
        if (!AppConstants.debug) {
            AppConstants.APP_CHANNEL = BaseUtils.getChannel(this);
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
