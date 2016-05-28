package me.santong.weather.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.models.weather.DailyForecast;
import me.santong.weather.utils.DBHelper;
import me.santong.weather.utils.DateUtils;
import me.santong.weather.utils.StringUtils;

/**
 * Created by santong.
 * At 16/5/27 11:09
 */
public class DailyItemAdapter extends RecyclerView.Adapter<DailyItemAdapter.ViewHolder> {

    private Context mContext;

    private List<DailyForecast> mDailyForecastList;

    private DBHelper dbHelper;

    public DailyItemAdapter(Context context, List<DailyForecast> dailyForecastList) {
        mContext = context;
        this.mDailyForecastList = dailyForecastList;

        dbHelper = DBHelper.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.item_daily, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyForecast dailyForecast = mDailyForecastList.get(position);

        holder.tvTmpRange.setText(StringUtils.decorateTmpRange(dailyForecast.getTmp().getMax()
                , dailyForecast.getTmp().getMin()));
        holder.tvWeekDay.setText(DateUtils.getDayOfWeek(dailyForecast.getDate()));

        String path = dbHelper.getWeatherIconPath(dailyForecast.getCond().getCode_d());
        Glide.with(mContext).load(path).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return mDailyForecastList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWeekDay;
        TextView tvTmpRange;
        ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            tvWeekDay = (TextView) itemView.findViewById(R.id.id_item_daily_tv_week_day);
            tvTmpRange = (TextView) itemView.findViewById(R.id.id_item_daily_tv_tmp_range);
            imgIcon = (ImageView) itemView.findViewById(R.id.id_item_daily_img_icon);
        }
    }
}
