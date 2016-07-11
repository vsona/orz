package net.vsona.orz;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 16/6/24.
 */
@Module
public class AppModule {
    private final OrzApplication application;

    public AppModule(OrzApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
    @Provides
    @Singleton
    SharedPreferences provideSp(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }
    /**
     *
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    ApiService providesApiService(RetrofitManager retrofitManager) {
        return retrofitManager.getJokeService();
    }

    @Provides
    @Singleton
    SpfManager provideSpfManager() {
        return new SpfManager(application);
    }

    @Provides
    @Singleton
    DBManager provideDBManager() {
        return new DBManager(application);
    }
     * @param jobExecutor
     * @return
     */
}