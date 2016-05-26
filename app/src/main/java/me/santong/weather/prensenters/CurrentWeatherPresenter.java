package me.santong.weather.prensenters;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import me.santong.weather.contracts.CurrentContract;
import me.santong.weather.utils.DateUtils;


/**
 * Created by santong.
 * At 16/5/25 17:29
 */
public class CurrentWeatherPresenter implements CurrentContract.UserListener {

    private CurrentContract.View mView;

    public CurrentWeatherPresenter(CurrentContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.init();
    }


    @Override
    public void getData(Bundle bundle) {
        String city = bundle.getString("city");
        String tmpRange = bundle.getString("tmpRange");
        String tmp = bundle.getString("tmp");
        String des = bundle.getString("des");
        Log.e("===", city + tmp + tmpRange + des);
        if (!TextUtils.isEmpty(tmp))
            mView.setCurrentTmp(" " + tmp + "°");
        if (!TextUtils.isEmpty(des))
            mView.setWeatherDes(des);
        if (!TextUtils.isEmpty(city))
            mView.setCityName(city);
        if (!TextUtils.isEmpty(tmpRange))
            mView.setTmpRange(tmpRange);
        mView.setDayOfWeek(DateUtils.getDayOfWeek());

    }
}
