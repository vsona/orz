package net.vsona.orz.api;

public class Api {

    private static IJokeApiService sJokeService;

    public static IJokeApiService getJokeService() {
        if (sJokeService == null) {
            sJokeService = RetrofitClient.createApi(IJokeApiService.class, RetrofitClient.getJokeRetrofit());
        }
        return sJokeService;
    }

}
