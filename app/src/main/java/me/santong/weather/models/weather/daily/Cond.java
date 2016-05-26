package me.santong.weather.models.weather.daily;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:24
 */
public class Cond extends Entity {
    private int code_d;
    private int code_n;
    private String txt_d;
    private String txt_n;

    public int getCode_d() {
        return code_d;
    }

    public void setCode_d(int code_d) {
        this.code_d = code_d;
    }

    public int getCode_n() {
        return code_n;
    }

    public void setCode_n(int code_n) {
        this.code_n = code_n;
    }

    public String getTxt_d() {
        return txt_d;
    }

    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }

    public String getTxt_n() {
        return txt_n;
    }

    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
    }

    @Override
    public String toString() {
        return "Cond{" +
                "txt_n='" + txt_n + '\'' +
                ", txt_d='" + txt_d + '\'' +
                ", code_n=" + code_n +
                ", code_d=" + code_d +
                '}';
    }
}
