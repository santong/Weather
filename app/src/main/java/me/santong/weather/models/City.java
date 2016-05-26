package me.santong.weather.models;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/25 17:00
 */
public class City extends Entity {
    private String city;
    private String cnty;
    private String id;      // id
    private double lat;
    private double lon;
    private String prov;    // 省份

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", id='" + id + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", prov='" + prov + '\'' +
                '}';
    }
}
