package me.santong.weather.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.adapters.DailyItemAdapter;
import me.santong.weather.framework.BaseFragment;
import me.santong.weather.models.weather.DailyForecast;

/**
 * Created by santong.
 * At 16/5/25 13:02
 */
public class DailyForecastFragment extends BaseFragment {

    public DailyForecastFragment() {
        super(R.layout.fragment_daily);
    }

    @Override
    protected void viewDidLoad() {
        RecyclerView rvDaily = (RecyclerView) findViewById(R.id.id_fg_daily_rv_daily_list);
        rvDaily.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));

        Bundle bundle = getArguments();
        List<DailyForecast> dailyForecastList = (List<DailyForecast>) bundle
                .getSerializable("dailyForecast");

        if (dailyForecastList != null) {
            dailyForecastList.remove(0);    // 去掉当天的天气预报
            DailyItemAdapter dailyItemAdapter = new DailyItemAdapter(getActivity(), dailyForecastList);
            rvDaily.setAdapter(dailyItemAdapter);
        }
    }
}
