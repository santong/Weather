package me.santong.weather.models.now;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:16
 */
public class Cond extends Entity {
    private int code;       // 天气代码
    private String txt;     // 天气描述

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Cond{" +
                "code=" + code +
                ", txt='" + txt + '\'' +
                '}';
    }
}
