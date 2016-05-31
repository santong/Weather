package me.santong.weather.fragments;

import android.os.Bundle;
import android.widget.TextView;

import me.santong.weather.R;
import me.santong.weather.framework.BaseFragment;
import me.santong.weather.models.weather.DailyForecast;

/**
 * Created by santong.
 * At 16/5/25 13:03
 */
public class WeatherDetailFragment extends BaseFragment {

    private TextView tvSummary;         // 综述
    private TextView tvSunrise;         // 日出时间
    private TextView tvSunset;          // 日落时间
    private TextView tvRainfallRate;    // 降水量
    private TextView tvHum;             // 湿度
    private TextView tvWindSpeed;       // 风速
    private TextView tvApparentTmp;     // 体感温度
    private TextView tvPrecipitation;   // 降水量
    private TextView tvAtmosPressure;   // 大气压
    private TextView tvVisibility;      // 能见度
    private TextView tvUv;              // 紫外线


    public WeatherDetailFragment() {
        super(R.layout.fragment_detail);
    }

    @Override
    protected void viewDidLoad() {
        tvSummary = (TextView) findViewById(R.id.id_fg_detail_tv_summary);
        tvSunrise = (TextView) findViewById(R.id.id_fg_detail_tv_sunrise);
        tvSunset = (TextView) findViewById(R.id.id_fg_detail_tv_sunset);
        tvRainfallRate = (TextView) findViewById(R.id.id_fg_detail_tv_rainfall_rate);
        tvHum = (TextView) findViewById(R.id.id_fg_detail_tv_hum);
        tvWindSpeed = (TextView) findViewById(R.id.id_fg_detail_tv_wind_speed);
        tvApparentTmp = (TextView) findViewById(R.id.id_fg_detail_tv_apparent_tmp);
        tvPrecipitation = (TextView) findViewById(R.id.id_fg_detail_tv_precipitation);
        tvAtmosPressure = (TextView) findViewById(R.id.id_fg_detail_tv_atmos_pressure);
        tvVisibility = (TextView) findViewById(R.id.id_fg_detail_tv_visibility);
        tvUv = (TextView) findViewById(R.id.id_fg_detail_tv_uv);

        Bundle bundle = getArguments();

        if (null == bundle)
            return;

        DailyForecast dailyForecast = (DailyForecast) bundle.getSerializable("toadyForecast");
        String uvStr = bundle.getString("uv");
        String fl = String.valueOf(bundle.getInt("feelTmp"));
        String condTxt = bundle.getString("cond");
        String nowTmp = String.valueOf(bundle.getInt("nowTmp"));

        if (dailyForecast != null) {
            tvSummary.setText("今天：现在" + condTxt + "。气温" + nowTmp + "°；"
                    + "预计最高气温" + dailyForecast.getTmp().getMax() + "°。");
            tvSunrise.setText(dailyForecast.getAstro().getSr());
            tvSunset.setText(dailyForecast.getAstro().getSs());
            tvRainfallRate.setText(dailyForecast.getPop() + "%");
            tvHum.setText(dailyForecast.getHum() + "%");
            tvWindSpeed.setText(judgeWindDirectionStr(dailyForecast.getWind().getDeg())
                    + " " + dailyForecast.getWind().getSpd() + " 米/秒");
            tvApparentTmp.setText(fl + "°");
            tvPrecipitation.setText(dailyForecast.getPcpn() + " mm");
            tvAtmosPressure.setText(dailyForecast.getPres() + " 百帕");
            tvVisibility.setText(dailyForecast.getVis() + " 公里");
            tvUv.setText(uvStr);
        }
    }

    private String judgeWindDirectionStr(int angle) {
        String angleStr = "";
        if (angle == 0 || angle == 360)
            angleStr = "东";
        else if (angle > 0 && angle < 45)
            angleStr = "东北偏东";
        else if (angle > 45 && angle < 90)
            angleStr = "东北偏北";
        else if (angle == 45)
            angleStr = "东北";
        else if (angle == 90)
            angleStr = "北";
        else if (angle > 90 && angle < 135)
            angleStr = "西北偏北";
        else if (angle > 135 && angle < 180)
            angleStr = "西北偏西";
        else if (angle == 180)
            angleStr = "西";
        else if (angle > 180 && angle < 225)
            angleStr = "西南偏西";
        else if (angle > 225 && angle < 270)
            angleStr = "西南偏南";
        else if (angle == 225)
            angleStr = "西南";
        else if (angle == 270)
            angleStr = "南";
        else if (angle > 270 && angle < 315)
            angleStr = "东南偏南";
        else if (angle > 315 && angle < 360)
            angleStr = "东南偏东";
        else if (angle == 315)
            angleStr = "东南";
        return angleStr;
    }
}
