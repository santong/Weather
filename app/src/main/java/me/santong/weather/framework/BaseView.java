package me.santong.weather.framework;

/**
 * Created by santong.
 * At 16/5/20 18:14
 */
public interface BaseView {
    void showProgress();

    void hideProgress();

    void showToast(String msg);
}
