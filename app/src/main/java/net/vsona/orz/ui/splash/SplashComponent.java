package net.vsona.orz.ui.splash;


import net.vsona.orz.ActivityScope;
import net.vsona.orz.AppComponent;
import net.vsona.orz.ui.splash.SplashActivity;
import net.vsona.orz.ui.splash.SplashPresenterModule;

import dagger.Component;

/**
 * Created by roy on 16/6/24.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {SplashPresenterModule.class})
public interface SplashComponent {

    void inject(SplashActivity activity);

    // ....
}
