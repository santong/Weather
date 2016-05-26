package me.santong.weather.models.weather;

import me.santong.weather.framework.Entity;
import me.santong.weather.models.weather.suggestion.Comf;
import me.santong.weather.models.weather.suggestion.Cw;
import me.santong.weather.models.weather.suggestion.Drsg;
import me.santong.weather.models.weather.suggestion.Flu;
import me.santong.weather.models.weather.suggestion.Sport;
import me.santong.weather.models.weather.suggestion.Trav;
import me.santong.weather.models.weather.suggestion.Uv;

/**
 * Created by santong.
 * At 16/5/24 22:00
 */
public class Suggestion extends Entity {
    private Comf comf;  // 舒适指数
    private Cw cw;      // 洗车指数
    private Drsg drsg;  // 穿衣指数
    private Flu flu;    // 感冒指数
    private Sport sport;// 运动指数
    private Trav trav;  // 旅游指数
    private Uv uv;      // 紫外线指数

    public Comf getComf() {
        return comf;
    }

    public void setComf(Comf comf) {
        this.comf = comf;
    }

    public Cw getCw() {
        return cw;
    }

    public void setCw(Cw cw) {
        this.cw = cw;
    }

    public Drsg getDrsg() {
        return drsg;
    }

    public void setDrsg(Drsg drsg) {
        this.drsg = drsg;
    }

    public Flu getFlu() {
        return flu;
    }

    public void setFlu(Flu flu) {
        this.flu = flu;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Trav getTrav() {
        return trav;
    }

    public void setTrav(Trav trav) {
        this.trav = trav;
    }

    public Uv getUv() {
        return uv;
    }

    public void setUv(Uv uv) {
        this.uv = uv;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "comf=" + comf.toString() +
                ", cw=" + cw.toString() +
                ", drsg=" + drsg.toString() +
                ", flu=" + flu.toString() +
                ", sport=" + sport.toString() +
                ", trav=" + trav.toString() +
                ", uv=" + uv.toString() +
                '}';
    }
}
