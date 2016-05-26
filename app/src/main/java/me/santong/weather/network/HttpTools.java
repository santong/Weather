package me.santong.weather.network;

import java.util.List;

import me.santong.weather.models.City;
import me.santong.weather.models.Condition;
import me.santong.weather.models.Weather;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
        @GET("weather")
        Observable<Response<Object>> getWeatherString(@Query("cityid") String cityId, @Query("key") String key);

        @GET("weather")
        Observable<Weather> getWeather(@Query("cityid") String cityId,@Query("key") String key);

        @GET("citylist")
        Observable<List<City>> getCityList(@Path("search") String searchType);

        @GET("condition")
        Observable<List<Condition>> getCondList(@Path("search") String searchType);
    }


}
