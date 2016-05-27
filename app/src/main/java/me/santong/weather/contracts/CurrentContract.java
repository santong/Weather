package me.santong.weather.contracts;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import me.santong.weather.framework.BaseUserListener;
import me.santong.weather.framework.BaseView;

/**
 * Created by santong.
 * At 16/5/25 17:27
 */
public interface CurrentContract {
    interface View extends BaseView {
        void init();

        void setCityName(String txt);

        void setWeatherDes(String txt);

        void setCurrentTmp(String txt);

        void setDayOfWeek(String txt);

        void setTmpRange(SpannableStringBuilder txt);
    }

    interface UserListener extends BaseUserListener {
        void getData(Bundle bundle);
    }
}
