package me.santong.weather.models;

import java.util.List;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:33
 */
public class Weather extends Entity {
    private Aqi aqi;
    private Basic basic;
    private List<DailyForecast> dailyForecastList;
    private List<HourlyForecast> hourlyForecastList;
    private NowWeather nowWeather;
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
        return dailyForecastList;
    }

    public void setDailyForecastList(List<DailyForecast> dailyForecastList) {
        this.dailyForecastList = dailyForecastList;
    }

    public List<HourlyForecast> getHourlyForecastList() {
        return hourlyForecastList;
    }

    public void setHourlyForecastList(List<HourlyForecast> hourlyForecastList) {
        this.hourlyForecastList = hourlyForecastList;
    }

    public NowWeather getNowWeather() {
        return nowWeather;
    }

    public void setNowWeather(NowWeather nowWeather) {
        this.nowWeather = nowWeather;
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
                ", dailyForecastList=" + dailyForecastList +
                ", hourlyForecastList=" + hourlyForecastList +
                ", nowWeather=" + nowWeather.toString() +
                ", status='" + status + '\'' +
                ", suggestion=" + suggestion.toString() +
                '}';
    }
}
