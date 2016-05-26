package me.santong.weather.prensenters;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import me.santong.weather.contracts.HomeContract;
import me.santong.weather.models.Weather;
import me.santong.weather.models.weather.DailyForecast;
import me.santong.weather.network.HttpTools;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santong.
 * At 16/5/26 15:42
 */
public class HomePresenter implements HomeContract.UserListener {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void LoadWeather() {
        HttpTools.onGet onGet = HttpTools.getRetrofit().create(HttpTools.onGet.class);
        onGet.getWeatherString("CN101010100", "20d53cdd5a3345a7a7dab43d46ef778d")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("===E", e.toString());
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<Object> response) {
                        Gson gson = new Gson();
                        JsonElement element = gson.toJsonTree(response.body());
                        Weather weather = gson.fromJson(element
                                .getAsJsonObject()
                                .getAsJsonArray("HeWeather data service 3.0")
                                .get(0), Weather.class);
//                        Log.e("===", weather.toString());
                        deliverData(weather);
                    }
                });
    }

    @Override
    public void start() {
        mView.init();
    }

    private void deliverData(Weather weather){
        DailyForecast todayForecast = weather.getDailyForecastList().get(0);

        // 传递数据给CurrentWeatherFragment
        Bundle cBundle = new Bundle();
        cBundle.putString("city", weather.getBasic().getCity());
        cBundle.putString("tmp", String.valueOf(weather.getNowWeather().getTmp()));
        cBundle.putString("tmpRange", todayForecast.getTmp().getMax()
                + "   " + todayForecast.getTmp().getMin());
        cBundle.putString("des", weather.getNowWeather().getCond().getTxt());
        mView.data4CurrentFgmt(cBundle);

    }
}
