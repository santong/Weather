package me.santong.weather.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.adapters.HourlyItemAdapter;
import me.santong.weather.framework.BaseFragment;
import me.santong.weather.models.weather.HourlyForecast;

/**
 * Created by santong.
 * At 16/5/25 13:01
 * 只做展示所以不必使用MVP;
 */
public class HourlyForecastFragment extends BaseFragment {

    public HourlyForecastFragment() {
        super(R.layout.fragment_hourly);
    }

    @Override
    protected void viewDidLoad() {
        TextView tvWarn = (TextView) findViewById(R.id.id_fg_hourly_tv_warn);
        RecyclerView rvHourly = (RecyclerView) findViewById(R.id.id_fg_hourly_rv_forecast_list);
        rvHourly.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.HORIZONTAL, false));

        Bundle bundle = getArguments();
        List<HourlyForecast> hourlyForecastList = (List<HourlyForecast>) bundle.getSerializable("hourlyForecast");

        if (hourlyForecastList == null) {
            tvWarn.setVisibility(View.VISIBLE);
            rvHourly.setVisibility(View.GONE);
        } else {
            tvWarn.setVisibility(View.GONE);
            rvHourly.setVisibility(View.VISIBLE);
            HourlyItemAdapter adapter = new HourlyItemAdapter(getActivity(), hourlyForecastList);
            rvHourly.setAdapter(adapter);
        }
    }
}
