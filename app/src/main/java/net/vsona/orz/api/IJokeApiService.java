package net.vsona.orz.api;

import net.vsona.orz.domain.JokeEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by roy on 16/6/28.
 */
public interface IJokeApiService {
    @Headers("apikey:83ec99fff780989a5376a1bc595ed5ff")
    @GET("showapi_joke/joke_text")
    Observable<JokeEntity> getJoke(@Query("page") int page);

    @GET("showapi_joke/joke_text")
    Call<JokeEntity> callJoke(@Header("apikey") String apikey, @Query("page") int page);
}
