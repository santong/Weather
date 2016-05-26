package me.santong.weather.models.weather;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 21:51
 */
public class Basic extends Entity {
    private String city;
    private String cnty;
    private String id;
    private double lat;
    private double lon;
    private String loc; //当地时间;
    private String utc; //更新时间

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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    @Override
    public String toString() {
        return "Basic{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", id='" + id + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", loc='" + loc + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }
}
