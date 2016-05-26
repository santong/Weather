package me.santong.weather.models;

import java.util.List;

import me.santong.weather.framework.Entity;
import me.santong.weather.models.weather.Aqi;
import me.santong.weather.models.weather.Basic;
import me.santong.weather.models.weather.DailyForecast;
import me.santong.weather.models.weather.HourlyForecast;
import me.santong.weather.models.weather.NowWeather;
import me.santong.weather.models.weather.Suggestion;

/**
 * Created by santong.
 * At 16/5/24 22:33
 */
public class Weather extends Entity {
    private Aqi aqi;
    private Basic basic;
    private List<DailyForecast> daily_forecast;
    private List<HourlyForecast> hourly_forecast;
    private NowWeather now;
    private String status;
    private Suggestion suggestion;

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public List<DailyForecast> getDailyForecastList() {
        return daily_forecast;
    }

    public void setDailyForecastList(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecast> getHourlyForecastList() {
        return hourly_forecast;
    }

    public void setHourlyForecastList(List<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public NowWeather getNowWeather() {
        return now;
    }

    public void setNowWeather(NowWeather now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "aqi=" + aqi.toString() +
                ", basic=" + basic.toString() +
                ", daily_forecast=" + daily_forecast.get(0) +
                ", hourly_forecast=" + hourly_forecast.size() +
                ", now=" + now.toString() +
                ", status='" + status + '\'' +
                ", suggestion=" + suggestion.toString() +
                '}';
    }
}
