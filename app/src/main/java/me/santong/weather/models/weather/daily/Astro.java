package me.santong.weather.models.weather.daily;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:23
 */
public class Astro extends Entity {
    private String sr;      // 日出时间
    private String ss;      // 日落时间

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    @Override
    public String toString() {
        return "Astro{" +
                "sr='" + sr + '\'' +
                ", ss='" + ss + '\'' +
                '}';
    }
}
