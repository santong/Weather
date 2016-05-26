package me.santong.weather.models.weather;

import me.santong.weather.framework.Entity;
import me.santong.weather.models.weather.api.City;

/**
 * Created by santong.
 * At 16/5/24 21:42
 * 空气质量指数
 */
public class Aqi extends Entity{

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "city=" + city +
                '}';
    }
}
