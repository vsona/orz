package net.vsona.orz.ui.joke;

import net.vsona.orz.ActivityScope;
import net.vsona.orz.ui.splash.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 16/6/24.
 */
@Module
public class JokePresenterModule {

    private final JokeContract.View mView;

    public JokePresenterModule(JokeContract.View view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    JokeContract.View view() {
        return this.mView;
    }
}
