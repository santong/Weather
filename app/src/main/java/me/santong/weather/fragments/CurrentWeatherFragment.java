package me.santong.weather.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import me.santong.weather.R;
import me.santong.weather.framework.BaseFragment;
import me.santong.weather.models.weather.Aqi;
import me.santong.weather.models.weather.api.City;
import me.santong.weather.utils.DateUtils;
import me.santong.weather.utils.StringUtils;

/**
 * Created by santong.
 * At 16/5/25 13:02
 */
public class CurrentWeatherFragment extends BaseFragment {

    private TextView tvCity;
    private TextView tvDes;
    private TextView tvTmp;
    private TextView tvDayOfWeek;
    private TextView tvTmpRange;

    private TextView tvQty;
    private TextView tvAqi;
    private TextView tvCO;
    private TextView tvNO2;
    private TextView tvO3;
    private TextView tvPm10;
    private TextView tvPm25;
    private TextView tvSO2;

    public CurrentWeatherFragment() {
        super(R.layout.fragment_current);
    }

    @Override
    protected void viewDidLoad() {
        initView();
        initData();
    }


    private void initView() {
        tvCity = (TextView) findViewById(R.id.id_fg_current_tv_city);
        tvDes = (TextView) findViewById(R.id.id_fg_current_tv_des);
        tvTmp = (TextView) findViewById(R.id.id_fg_current_tv_tmp);
        tvDayOfWeek = (TextView) findViewById(R.id.id_fg_current_tv_week_day);
        tvTmpRange = (TextView) findViewById(R.id.id_fg_current_tv_tmp_range);

        tvQty = (TextView) findViewById(R.id.id_fg_current_tv_quality);
        tvAqi = (TextView) findViewById(R.id.id_fg_current_tv_aqi);
        tvCO = (TextView) findViewById(R.id.id_fg_current_tv_co);
        tvNO2 = (TextView) findViewById(R.id.id_fg_current_tv_no2);
        tvO3 = (TextView) findViewById(R.id.id_fg_current_tv_o3);
        tvPm10 = (TextView) findViewById(R.id.id_fg_current_tv_pm10);
        tvPm25 = (TextView) findViewById(R.id.id_fg_current_tv_pm25);
        tvSO2 = (TextView) findViewById(R.id.id_fg_current_tv_so2);
    }

    private void initData() {

        // 当天基本天气部分
        Bundle bundle = getArguments();
        String cityName = bundle.getString("city");
        int tmpMax = bundle.getInt("tmpMax");
        int tmpMin = bundle.getInt("tmpMin");
        String tmp = bundle.getString("tmp");
        String des = bundle.getString("des");

        if (!TextUtils.isEmpty(tmp))
            tvTmp.setText(" " + tmp + "°");

        if (!TextUtils.isEmpty(des))
            tvDes.setText(des);
        if (!TextUtils.isEmpty(cityName))
            tvCity.setText(cityName + "市");
        tvTmpRange.setText(StringUtils.decorateTmpRange(tmpMax, tmpMin));
        tvDayOfWeek.setText(DateUtils.getDayOfWeek());

        // 当天空气指数部分
        Aqi aqi = (Aqi) bundle.getSerializable("aqi");
        City city = null;
        if (aqi != null) {
            city = aqi.getCity();
        }
        if (city != null) {
            tvQty.setText(city.getQlty());
            tvAqi.setText(String.valueOf(city.getAqi()));
            tvCO.setText(String.valueOf(city.getCo()));
            tvNO2.setText(String.valueOf(city.getNo2()));
            tvO3.setText(String.valueOf(city.getO3()));
            tvPm10.setText(String.valueOf(city.getPm10()));
            tvPm25.setText(String.valueOf(city.getPm25()));
            tvSO2.setText(String.valueOf(city.getSo2()));
        }

    }
}
