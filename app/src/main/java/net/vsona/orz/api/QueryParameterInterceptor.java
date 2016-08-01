package net.vsona.orz.api;

import android.text.TextUtils;

import net.vsona.orz.utils.AppConstants;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by roy on 16/6/27.
 */
public class QueryParameterInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        String method = originalRequest.method();
        Headers headers = originalRequest.headers();
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter(AppConstants.FILED_PLATFORM, AppConstants.sAppPlatform)
                .addQueryParameter(AppConstants.FILED_VERSION, AppConstants.sAppVersion + "")
                .addQueryParameter(AppConstants.FILED_CHANNEL, AppConstants.sAppChannel)
                .build();

        //new builder
        Request.Builder builder = originalRequest.newBuilder();

        if(TextUtils.equals(originalRequest.header("Content-Encoding"), "gzip")){
            builder.header("Content-Encoding", "gzip");
//                builder.method(originalRequest.method(), gzip(originalRequest.body()));
        }

        builder.url(modifiedUrl);

        request = builder.build();
        return chain.proceed(request);
    }
}
