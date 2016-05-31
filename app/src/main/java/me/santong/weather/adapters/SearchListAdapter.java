package me.santong.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.models.City;

/**
 * Created by santong.
 * At 16/5/30 22:09
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchViewHolder> {

    private Context mContext;
    private List<City> mCityList;

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(SearchListAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onClick(View view, int pos);
    }

    public SearchListAdapter(Context context, List<City> cityList) {
        mContext = context;
        mCityList = cityList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.item_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, final int position) {
        City city = mCityList.get(position);

        holder.tvCityNameCN.setText(city.getCity());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView tvCityNameCN;

        public SearchViewHolder(View itemView) {
            super(itemView);

            tvCityNameCN = (TextView) itemView.findViewById(R.id.id_item_search_result_tv_cn);
        }
    }
}
