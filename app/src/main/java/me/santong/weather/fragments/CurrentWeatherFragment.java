package me.santong.weather.fragments;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import me.santong.weather.R;
import me.santong.weather.contracts.CurrentContract;
import me.santong.weather.framework.BaseFragment;
import me.santong.weather.prensenters.CurrentWeatherPresenter;

/**
 * Created by santong.
 * At 16/5/25 13:02
 */
public class CurrentWeatherFragment extends BaseFragment implements CurrentContract.View {

    private TextView tvCity;
    private TextView tvDes;
    private TextView tvTmp;
    private TextView tvDayOfWeek;
    private TextView tvTmpRange;
    private CurrentContract.UserListener mPresenter;

    public CurrentWeatherFragment() {
        super(R.layout.fragment_current);
    }

    @Override
    protected void viewDidLoad() {
        mPresenter = new CurrentWeatherPresenter(this);
        mPresenter.start();
    }


    @Override
    public void init() {
        tvCity = (TextView) findViewById(R.id.id_fg_current_tv_city);
        tvDes = (TextView) findViewById(R.id.id_fg_current_tv_des);
        tvTmp = (TextView) findViewById(R.id.id_fg_current_tv_tmp);
        tvDayOfWeek = (TextView) findViewById(R.id.id_fg_current_tv_week_day);
        tvTmpRange = (TextView) findViewById(R.id.id_fg_current_tv_tmp_range);

        Bundle bundle = getArguments();
        mPresenter.getData(bundle);
    }

    @Override
    public void setCityName(String txt) {
        tvCity.setText(txt);
    }

    @Override
    public void setWeatherDes(String txt) {
        tvDes.setText(txt);
    }

    @Override
    public void setCurrentTmp(String txt) {
        tvTmp.setText(txt);
    }

    @Override
    public void setDayOfWeek(String txt) {
        tvDayOfWeek.setText(txt);
    }

    @Override
    public void setTmpRange(String txt) {
        tvTmpRange.setText(txt);
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
