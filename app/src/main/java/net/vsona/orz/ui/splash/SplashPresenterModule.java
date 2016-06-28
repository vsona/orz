package net.vsona.orz.ui.splash;

import net.vsona.orz.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 16/6/24.
 */
@Module
public class SplashPresenterModule {

    private final SplashContract.View mView;

    public SplashPresenterModule(SplashContract.View view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    SplashContract.View view() {
        return this.mView;
    }
}
