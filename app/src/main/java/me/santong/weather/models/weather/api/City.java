package me.santong.weather.models.weather.api;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/25 23:50
 */
public class City extends Entity {

    private int aqi;
    private int co;
    private int no2;
    private int o3;
    private int pm10;
    private int pm25;
    private String qlty;
    private int so2;

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public int getCo() {
        return co;
    }

    public void setCo(int co) {
        this.co = co;
    }

    public int getNo2() {
        return no2;
    }

    public void setNo2(int no2) {
        this.no2 = no2;
    }

    public int getO3() {
        return o3;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public int getSo2() {
        return so2;
    }

    public void setSo2(int so2) {
        this.so2 = so2;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "aqi=" + aqi +
                ", co=" + co +
                ", no2=" + no2 +
                ", o3=" + o3 +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                ", qlty='" + qlty + '\'' +
                ", so2=" + so2 +
                '}';

    }
}
