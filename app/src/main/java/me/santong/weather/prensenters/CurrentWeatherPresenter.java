package me.santong.weather.prensenters;

import android.os.Bundle;
import android.text.TextUtils;

import me.santong.weather.contracts.CurrentContract;
import me.santong.weather.utils.DateUtils;
import me.santong.weather.utils.StringUtils;


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
        int tmpMax = bundle.getInt("tmpMax");
        int tmpMin = bundle.getInt("tmpMin");
        String tmp = bundle.getString("tmp");
        String des = bundle.getString("des");

        if (!TextUtils.isEmpty(tmp))
            mView.setCurrentTmp(" " + tmp + "°");
        if (!TextUtils.isEmpty(des))
            mView.setWeatherDes(des);
        if (!TextUtils.isEmpty(city))
            mView.setCityName(city + "市");

        mView.setTmpRange(StringUtils.decorateTmpRange(tmpMax, tmpMin));
        mView.setDayOfWeek(DateUtils.getDayOfWeek());
    }
}
