package net.vsona.orz.api;

public class Api {

    public static IApiService getService(){
        return RetrofitClient.createApi(IApiService.class);
    }

}
