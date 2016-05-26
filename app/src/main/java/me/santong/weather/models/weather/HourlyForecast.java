package me.santong.weather.models.weather;


import me.santong.weather.framework.Entity;
import me.santong.weather.models.weather.now.Wind;

/**
 * Created by santong.
 * At 16/5/24 22:29
 */
public class HourlyForecast extends Entity {
    private String date;
    private int hum;        // 湿度
    private double pop;     // 降水概率
    private int pres;       // 气压
    private int tmp;        // 当前温度
    private Wind wind;

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

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "HourlyForecast{" +
                "date='" + date + '\'' +
                ", hum=" + hum +
                ", pop=" + pop +
                ", pres=" + pres +
                ", tmp=" + tmp +
                ", wind=" + wind.toString() +
                '}';
    }
}
