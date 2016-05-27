package me.santong.weather.models.weather.daily;

import java.io.Serializable;

import me.santong.weather.framework.Entity;

/**
 * Created by santong.
 * At 16/5/24 22:25
 */
public class Tmp extends Entity implements Serializable{
    private int max;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Tmp{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
