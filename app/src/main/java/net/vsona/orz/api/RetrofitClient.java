package net.vsona.orz.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.vsona.orz.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit getJokeRetrofit() {

        Gson gson = MyGson.get();
        OkHttpClient client = MyOkClient.getOkHttpClient();

        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_JOKE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T createApi(Class<T> clazz, Retrofit retrofit) {
        return retrofit.create(clazz);
    }

}
