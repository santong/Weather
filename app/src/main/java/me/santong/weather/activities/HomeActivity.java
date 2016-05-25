package me.santong.weather.activities;

import android.os.Bundle;

import me.santong.weather.R;
import me.santong.weather.framework.BaseActivity;

public class HomeActivity extends BaseActivity {

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

    }
}
