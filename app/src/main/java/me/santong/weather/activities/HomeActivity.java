package me.santong.weather.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import me.santong.weather.R;
import me.santong.weather.contracts.HomeContract;
import me.santong.weather.fragments.CurrentWeatherFragment;
import me.santong.weather.fragments.DailyForecastFragment;
import me.santong.weather.fragments.HourlyForecastFragment;
import me.santong.weather.fragments.WeatherDetailFagment;
import me.santong.weather.framework.BaseActivity;
import me.santong.weather.prensenters.HomePresenter;

public class HomeActivity extends BaseActivity implements HomeContract.View{

    private HomeContract.UserListener mPresenter;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initActivity() {
        mPresenter = new HomePresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        fm = getSupportFragmentManager();

        mPresenter.LoadWeather();
    }

    @Override
    public void data4CurrentFgmt(Bundle bundle) {
        Fragment currentFragment = new CurrentWeatherFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        currentFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_content,currentFragment);
        transaction.commit();
    }

    @Override
    public void data4DailyFgmt(Bundle bundle) {
        Fragment dailyFragment = new DailyForecastFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        dailyFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_daily_content,dailyFragment);
        transaction.commit();
    }

    @Override
    public void data4HourlyFgmt(Bundle bundle) {
        Fragment hourlyFragment = new HourlyForecastFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        hourlyFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_hourly_content,hourlyFragment);
        transaction.commit();
    }

    @Override
    public void data4DetailFgmt(Bundle bundle) {
        Fragment detailFagment = new WeatherDetailFagment();
        FragmentTransaction transaction = fm.beginTransaction();
        detailFagment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_detail_content,detailFagment);
        transaction.commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String msg) {

    }
}
