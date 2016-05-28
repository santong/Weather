package me.santong.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        calendar.setTime(getDateFromStr(dateStr, 0));
        return getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * @param time 传入秒数
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

    /**
     * 将"yyyy-MM-dd HH:mm"格式的字符串转换成Date
     *
     * @param dateStr 日期字符
     * @return date
     */
    public static Date getDateFromStr(String dateStr, int type) {
        Date date;
        SimpleDateFormat sdf;
        switch (type) {
            case 0:
                sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("CN"));
                break;
            case 1:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("CN"));
                break;
            default:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("CN"));
                break;
        }
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }


    /**
     * @param dateStr 日期字符串
     * @return 取出字符串中的时间并进行判断, 返回值形如"上午11时;下午3时"
     */
    public static String getCurrentHour(String dateStr) {
        String hourTxt;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromStr(dateStr, 1));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 12)
            hourTxt = "下午" + (hour - 12) + "时";
        else
            hourTxt = "上午" + hour + "时";
        return hourTxt;
    }
}
