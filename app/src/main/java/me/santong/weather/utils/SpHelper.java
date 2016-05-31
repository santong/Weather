package me.santong.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Set;
import java.util.TreeSet;

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

    public boolean haveSelectedCity() {
        return getCitySet().size() > 0;
    }

    public void clearCityStr() {
        sharedPreferences.edit().remove("citySet").apply();
    }

    public void savePreferCitySet(Set<String> stringSet) {
        clearCityStr();
        sharedPreferences.edit().putStringSet("citySet", stringSet).apply();
    }

    public Set<String> getCitySet() {
        return sharedPreferences.getStringSet("citySet", new TreeSet<String>());
    }

}
