package me.santong.weather.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.models.weather.HourlyForecast;
import me.santong.weather.utils.DateUtils;

/**
 * Created by santong.
 * At 16/5/26 21:47
 */
public class HourlyItemAdapter extends RecyclerView.Adapter<HourlyItemAdapter.ViewHolder> {

    private Context mContext;
    private List<HourlyForecast> mHourlyForecastList;

    public HourlyItemAdapter(Context context, List<HourlyForecast> hourlyForecastList) {
        mContext = context;
        this.mHourlyForecastList = hourlyForecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_hourly, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HourlyForecast hourlyForecast = mHourlyForecastList.get(position);
        holder.tvTmp.setText(String.valueOf(hourlyForecast.getTmp()) + "°");
        holder.tvTime.setText(DateUtils.getCurrentHour(hourlyForecast.getDate()));
    }

    @Override
    public int getItemCount() {
        return mHourlyForecastList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvTmp;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTime = (TextView) itemView.findViewById(R.id.id_item_hourly_tv_time);
            tvTmp = (TextView) itemView.findViewById(R.id.id_item_hourly_tv_tmp);
        }
    }
}
