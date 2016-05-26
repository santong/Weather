package me.santong.weather.models.weather;

import me.santong.weather.framework.Entity;
import me.santong.weather.models.weather.daily.Astro;
import me.santong.weather.models.weather.daily.Cond;
import me.santong.weather.models.weather.daily.Tmp;
import me.santong.weather.models.weather.now.Wind;

/**
 * Created by santong.
 * At 16/5/24 22:21
 */
public class DailyForecast extends Entity {
    private Astro astro;
    private Cond cond;
    private String date;
    private int hum;        // 湿度
    private double pcpn;    // 降雨量(mm)
    private double pop;     // 降水概率
    private int pres;       // 气压
    private Tmp tmp;
    private double vis;     // 能见度
    private Wind wind;

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public double getPcpn() {
        return pcpn;
    }

    public void setPcpn(double pcpn) {
        this.pcpn = pcpn;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public Tmp getTmp() {
        return tmp;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    public double getVis() {
        return vis;
    }

    public void setVis(double vis) {
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
        return "DailyForecast{" +
                "astro=" + astro.toString() +
                ", cond=" + cond.toString() +
                ", date='" + date + '\'' +
                ", hum=" + hum +
                ", pcpn=" + pcpn +
                ", pop=" + pop +
                ", pres=" + pres +
                ", tmp=" + tmp.toString() +
                ", vis=" + vis +
                ", wind=" + wind.toString() +
                '}';
    }
}
