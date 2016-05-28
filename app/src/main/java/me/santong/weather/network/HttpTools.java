package me.santong.weather.network;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by santong.
 * At 16/5/25 16:36
 */
public class HttpTools {

    private static String baseUrl = "https://api.heweather.com/x3/";

    private final static String key = "20d53cdd5a3345a7a7dab43d46ef778d";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public interface onGet {
        @GET("weather?key=" + key)
        Observable<Response<Object>> getWeatherString(@Query("cityid") String cityId);

        @GET("citylist?key=" + key)
        Observable<Response<Object>> getCityListString(@Query("search") String searchType);

        @GET("condition?key=" + key)
        Observable<Response<Object>> getCondListString(@Query("search") String searchType);
    }


}
