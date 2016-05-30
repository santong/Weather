package me.santong.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by santong.
 * At 16/5/29 23:01
 */
public class SpHelper {
    private static SpHelper instance;

    private SharedPreferences sharedPreferences;

    public static SpHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (SpHelper.class) {
                if (instance == null) {
                    instance = new SpHelper();
                    instance.sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(context);
                }
            }
        }
        return instance;
    }

    public void savePreferCityList(Set<String> citySet) {
        sharedPreferences.edit().putStringSet("cityList", citySet).apply();
    }

    public Set<String> getCityList() {
        return sharedPreferences.getStringSet("cityList", null);
    }


}
