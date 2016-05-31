package me.santong.weather.prensenters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import me.santong.weather.R;
import me.santong.weather.contracts.HomeContract;
import me.santong.weather.models.Weather;
import me.santong.weather.models.weather.DailyForecast;
import me.santong.weather.network.HttpTools;
import me.santong.weather.utils.DBHelper;
import me.santong.weather.utils.SpHelper;
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

    private DBHelper dbHelper;

    private SpHelper spHelper;

    private Set<String> mCitySet;

    public HomePresenter(Context context, HomeContract.View mView) {
        this.mView = mView;
        spHelper = SpHelper.getInstance(context);
        dbHelper = DBHelper.getInstance(context);

        mCitySet = spHelper.getCitySet();
    }

    @Override
    public void start() {
        mView.init();
        setUpDrawerListData();

//        spHelper.clearCityStr();

        if (!spHelper.haveSelectedCity()) {
            showDefaultView();
            mView.showToastLong("暂未设置所在城市,请选择你所在的城市");
            mView.showSearchCityDialog();
        }

        if (null != mCitySet && mCitySet.size() > 0)
            LoadWeather((String) mCitySet.toArray()[0]);
    }

    @Override
    public void LoadWeather(String cityName) {
        mView.showProgress();

        saveCityToPrefer(cityName);
        String code = dbHelper.getCityCode(cityName);

//        spHelper.clearCityStr();

        // 防止输入城市名不存在引发问题
        if (TextUtils.isEmpty(code)) {
            mView.hideProgress();
            mView.showToast("对不起,查询不到结果");
            return;
        }

        HttpTools.onGet onGet = HttpTools.getRetrofit().create(HttpTools.onGet.class);
        onGet.getWeatherString(code)
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
    public void selectDrawerItem(int position) {
        if (position == 1)
            mView.showSearchCityDialog();
        else if (position > 1) {
            LoadWeather((String) mCitySet.toArray()[position - 2]);
        }
        mView.hideDrawer();
    }

    @Override
    public void saveCityList() {
        if (null != mCitySet && mCitySet.size() > 0)
            spHelper.savePreferCitySet(mCitySet);
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
        cBundle.putSerializable("aqi", weather.getAqi());
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

        // 传递数据给WeatherDetailFragment
        Bundle dtBundle = new Bundle();
        dtBundle.putSerializable("toadyForecast", todayForecast);
        dtBundle.putInt("feelTmp", weather.getNowWeather().getFl());
        dtBundle.putString("uv", weather.getSuggestion().getUv().getBrf());
        dtBundle.putString("cond", weather.getNowWeather().getCond().getTxt());
        dtBundle.putInt("nowTmp", weather.getNowWeather().getTmp());
        mView.data4DetailFgmt(dtBundle);
    }

    private void setUpDrawerListData() {
        List<IDrawerItem> drawerItemList = new LinkedList<>();

        // 搜索Item
        PrimaryDrawerItem searchItem = new PrimaryDrawerItem();
        searchItem.withIcon(R.drawable.ic_search_black_18dp);
        searchItem.withName("添加城市");
        drawerItemList.add(searchItem);

        if (null != mCitySet) {
            for (String cityName : mCitySet) {
                PrimaryDrawerItem item = new PrimaryDrawerItem();
                item.withName(cityName);
                item.withIcon(R.drawable.ic_location_on_black_18dp);
                drawerItemList.add(item);
            }
        }

        mView.setDrawerData(drawerItemList);
    }

    private void saveCityToPrefer(String cityName) {
        if (null == mCitySet || mCitySet.contains(cityName))
            return;

        mCitySet.add(cityName);
        setUpDrawerListData();
    }

    private void showDefaultView() {
        mView.data4DetailFgmt(null);
        mView.data4DailyFgmt(null);
        mView.data4HourlyFgmt(null);
        mView.data4CurrentFgmt(null);
    }

}
