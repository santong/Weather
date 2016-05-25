package me.santong.weather.models;

import me.santong.weather.framework.Entity;
import me.santong.weather.models.now.Cond;
import me.santong.weather.models.now.Wind;

/**
 * Created by santong.
 * At 16/5/24 22:13
 * 当前天气
 */
public class NowWeather extends Entity {
    private Cond cond;  // 天气描述
    private int fl;     // 体感温度
    private int hum;    // 湿度(%)
    private int pcpn;   // 降雨量(mm)
    private int pres;   // 气压
    private int tmp;    // 当前温度(摄氏度)
    private int vis;    // 能见度(km)
    private Wind wind;  // 风力相关

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }

    public int getHuml() {
        return hum;
    }

    public void setHuml(int hum) {
        this.hum = hum;
    }

    public int getPcpn() {
        return pcpn;
    }

    public void setPcpn(int pcpn) {
        this.pcpn = pcpn;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }

    public int getVis() {
        return vis;
    }

    public void setVis(int vis) {
        this.vis = vis;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "NowWeather{" +
                "wind=" + wind.toString() +
                ", vis=" + vis +
                ", tmp=" + tmp +
                ", pres=" + pres +
                ", pcpn=" + pcpn +
                ", hum=" + hum +
                ", fl=" + fl +
                ", cond=" + cond.toString() +
                '}';
    }
}
