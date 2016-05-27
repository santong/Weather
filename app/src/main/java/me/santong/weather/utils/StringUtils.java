package me.santong.weather.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * Created by santong.
 * At 16/5/27 20:43
 */
public class StringUtils {

    public static SpannableString getTextWithColor(String str, String colorStr) {
        SpannableString spannable = new SpannableString(str);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor(colorStr))
                , 0, spannable.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannable;
    }

    public static SpannableStringBuilder decorateTmpRange(int max, int min) {
        String tmpMaxStr = String.valueOf(max);
        String tmpMinStr = String.valueOf(min);

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.insert(0, getTextWithColor(tmpMaxStr, "#e6ffffff"));
        ssb.insert(tmpMaxStr.length(), "   ");
        ssb.insert(tmpMaxStr.length() + 3, getTextWithColor(tmpMinStr, "#afffffff"));

        return ssb;
    }
}
