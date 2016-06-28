package net.vsona.orz;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by roy on 16/6/24.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();  // 提供Applicaiton的Context
    void inject(OrzApplication application);
//    void inject(BaseAppCompatActivity baseAppCompatActivity);
//    void inject(SplashActivity activity);

//    ThreadExecutor threadExecutor();   // 线程池

//    ApiService apiService();  // 所有Api请求的管理类

//    SpfManager spfManager();  // SharedPreference管理类

//    DBManager dbManager();  // 数据库管理类
}
