package me.santong.weather.models.now;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:18
 */
public class Wind extends Entity {
    private int deg;        // 风向(角度)
    private String dir;     // 风向(方向)
    private String sc;      // 风力等级
    private int spd;        // 风速

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "spd=" + spd +
                ", sc='" + sc + '\'' +
                ", dir='" + dir + '\'' +
                ", deg=" + deg +
                '}';
    }
}
