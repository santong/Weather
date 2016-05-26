package me.santong.weather.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by santong.
 * At 16/5/26 20:33
 */
public class DateUtils {

    /**
     * 返回今天是周几
     */
    public static String getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * @param i 根据数字判断,1(周日) ~ 7(周六)
     *          返回今天是周几
     */
    public static String getDayOfWeek(int i) {
        String dayOfWeek = null;
        switch (i) {
            case 1:
                dayOfWeek = "星期日";
                break;
            case 2:
                dayOfWeek = "星期一";
                break;
            case 3:
                dayOfWeek = "星期二";
                break;
            case 4:
                dayOfWeek = "星期三";
                break;
            case 5:
                dayOfWeek = "星期四";
                break;
            case 6:
                dayOfWeek = "星期五";
                break;
            case 7:
                dayOfWeek = "星期六";
                break;
            default:
                break;
        }
        return dayOfWeek;
    }

    /**
     * @param date 根据日期判断
     *             返回今天是周几
     */
    public static String getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * @param dateStr 根据日期字符串判断
     *                返回今天是周几
     */
    public static String getDayOfWeek(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromTimeString(dateStr));
        return getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * @param time 将日期字符串转为日期对象
     */
    public static Date getDateFromTimeString(String time) {
        try {
            Long longTime = Long.parseLong(time);
            return new Date(longTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
