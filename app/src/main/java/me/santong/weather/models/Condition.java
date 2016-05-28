package me.santong.weather.models;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/25 17:05
 */
public class Condition extends Entity {
    private int code;
    private String txt;
    private String txt_en;  // 英文
    private String icon;     // 图片路径

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

    public String getTxt_en() {
        return txt_en;
    }

    public void setTxt_en(String txt_en) {
        this.txt_en = txt_en;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "code=" + code +
                ", txt='" + txt + '\'' +
                ", txt_en='" + txt_en + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
