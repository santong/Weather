package me.santong.weather.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.LinkedList;
import java.util.List;

import me.santong.weather.R;
import me.santong.weather.contracts.HomeContract;
import me.santong.weather.fragments.CurrentWeatherFragment;
import me.santong.weather.fragments.DailyForecastFragment;
import me.santong.weather.fragments.HourlyForecastFragment;
import me.santong.weather.fragments.SearchCityDialogFragment;
import me.santong.weather.fragments.WeatherDetailFragment;
import me.santong.weather.framework.BaseActivity;
import me.santong.weather.prensenters.HomePresenter;

public class HomeActivity extends BaseActivity implements HomeContract.View
        , SearchCityDialogFragment.SearchCallbackListener {

    private HomeContract.UserListener mPresenter;

    private Drawer cityDrawer;

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
        mPresenter = new HomePresenter(this, this);
        mPresenter.start();
    }

    @Override
    public void init() {
        fm = getSupportFragmentManager();

        mPresenter.LoadWeather("厦门");
    }

    @Override
    public void setDrawerData(List<IDrawerItem> drawerItemList) {
        cityDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.header_layout)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mPresenter.selectDrawerItem(position);
                        return true;
                    }
                })
                .build();
        cityDrawer.setItems(drawerItemList);
    }

    @Override
    public void data4CurrentFgmt(Bundle bundle) {
        Fragment currentFragment = new CurrentWeatherFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        currentFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_content, currentFragment);
        transaction.commit();
    }

    @Override
    public void data4DailyFgmt(Bundle bundle) {
        Fragment dailyFragment = new DailyForecastFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        dailyFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_daily_content, dailyFragment);
        transaction.commit();
    }

    @Override
    public void data4HourlyFgmt(Bundle bundle) {
        Fragment hourlyFragment = new HourlyForecastFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        hourlyFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_hourly_content, hourlyFragment);
        transaction.commit();
    }

    @Override
    public void data4DetailFgmt(Bundle bundle) {
        Fragment detailFragment = new WeatherDetailFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        detailFragment.setArguments(bundle);
        transaction.replace(R.id.id_home_current_detail_content, detailFragment);
        transaction.commit();
    }

    @Override
    public void showSearchCityDialog() {
        cityDrawer.closeDrawer();

        SearchCityDialogFragment dialogFragment = new SearchCityDialogFragment();
        dialogFragment.show(getFragmentManager(), "searchDialog");
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallbackCity(String city) {
        mPresenter.LoadWeather(city);
    }
}
