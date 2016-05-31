package me.santong.weather.activities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import me.santong.weather.R;
import me.santong.weather.framework.BaseActivity;
import me.santong.weather.models.City;
import me.santong.weather.models.Condition;
import me.santong.weather.network.HttpTools;
import me.santong.weather.utils.DBHelper;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santong.
 * At 16/5/28 19:02
 */
public class SplashActivity extends BaseActivity {

    private DBHelper dbHelper;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initActivity() {
        dbHelper = DBHelper.getInstance(this);

        HttpTools.onGet onGet = HttpTools.getRetrofit().create(HttpTools.onGet.class);

        Observable.merge(onGet.getCondListString("allcond"), onGet.getCityListString("allchina"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        pushActivity(HomeActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pushActivity(HomeActivity.class);
                        finish();
                    }

                    @Override
                    public void onNext(Response<Object> response) {
                        Gson gson = new Gson();
                        JsonElement element = gson.toJsonTree(response.body());

                        JsonArray condArray = element.getAsJsonObject().getAsJsonArray("cond_info");
                        JsonArray cityArray = element.getAsJsonObject().getAsJsonArray("city_info");

                        if (condArray != null) {
                            Type condListType = new TypeToken<List<Condition>>() {
                            }.getType();
                            List<Condition> condList = gson.fromJson(condArray, condListType);
                            dbHelper.SaveOrUpdateCondList(condList);

                        } else if (cityArray != null) {
                            Type cityListType = new TypeToken<List<City>>() {
                            }.getType();
                            List<City> cityList = gson.fromJson(cityArray, cityListType);
                            dbHelper.SaveOrUpdateCityList(cityList);
                        }

                    }
                });
    }
}
