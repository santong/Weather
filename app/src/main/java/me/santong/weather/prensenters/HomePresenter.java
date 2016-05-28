package me.santong.weather.prensenters;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;

import me.santong.weather.contracts.HomeContract;
import me.santong.weather.models.Weather;
import me.santong.weather.models.weather.DailyForecast;
import me.santong.weather.network.HttpTools;
import retrofit2.Response;
import rx.Subscriber;
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
        mView.showProgress();
        HttpTools.onGet onGet = HttpTools.getRetrofit().create(HttpTools.onGet.class);
        onGet.getWeatherString("CN101230201")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("===E", e.toString());
                        mView.hideProgress();
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
                        deliverData(weather);
                    }
                });
    }

    @Override
    public void start() {
        mView.init();
    }

    private void deliverData(Weather weather) {
        DailyForecast todayForecast = weather.getDailyForecastList().get(0);

        // 传递数据给CurrentWeatherFragment
        Bundle cBundle = new Bundle();
        cBundle.putString("city", weather.getBasic().getCity());
        cBundle.putString("tmp", String.valueOf(weather.getNowWeather().getTmp()));
        cBundle.putInt("tmpMax", todayForecast.getTmp().getMax());
        cBundle.putInt("tmpMin", todayForecast.getTmp().getMin());
        cBundle.putString("des", todayForecast.getCond().getTxt_d());
        mView.data4CurrentFgmt(cBundle);

        // 传递数据给HourlyForecastFragment
        Bundle hBundle = new Bundle();
        // 晚上11点~12点间可能为null
        if (weather.getHourlyForecastList() != null) {
            hBundle.putSerializable("hourlyForecast", (Serializable) weather.getHourlyForecastList());
            mView.data4HourlyFgmt(hBundle);
        }

        // 传递数据给DailyForecastFragment
        Bundle dBundle = new Bundle();
        if (weather.getDailyForecastList() != null) {
            dBundle.putSerializable("dailyForecast", (Serializable) weather.getDailyForecastList());
            mView.data4DailyFgmt(dBundle);
        }

        Bundle dtBundle = new Bundle();
        dtBundle.putSerializable("toadyForecast", todayForecast);
        dtBundle.putInt("feelTmp", weather.getNowWeather().getFl());
        dtBundle.putString("uv", weather.getSuggestion().getUv().getBrf());
        dtBundle.putString("cond", weather.getNowWeather().getCond().getTxt());
        dtBundle.putInt("nowTmp", weather.getNowWeather().getTmp());
        mView.data4DetailFgmt(dtBundle);
    }
}
