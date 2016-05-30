package me.santong.weather.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.util.List;

import me.santong.weather.R;
import me.santong.weather.adapters.SearchListAdapter;
import me.santong.weather.models.City;
import me.santong.weather.utils.DBHelper;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santong.
 * At 16/5/30 19:39
 */
public class SearchCityDialogFragment extends DialogFragment implements View.OnClickListener {

    // 其实做到这里想了一下 其实普通的TextView也可以满足需要,动态提示才需要用这个吧
    private SearchView svSearch;
    private RecyclerView rvResultList;
    private Button btnSearch;

    private DBHelper dbHelper;

    private View rootView;

    private List<City> mSearchResultList;

    @Override
    public void onClick(View v) {
        if (v == btnSearch)
            doSearch();
    }

    public interface SearchCallbackListener {
        void onCallbackCity(String city);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        rootView = inflater.inflate(R.layout.fragment_dialog_search, null);

        initView();
        initData();
        initEvent();


        builder.setView(rootView)
                .setNegativeButton("取消", null);

        return builder.create();
    }

    private void initView() {
        svSearch = (SearchView) rootView.findViewById(R.id.id_fg_search_dialog_sv);
        rvResultList = (RecyclerView) rootView.findViewById(R.id.id_fg_search_dialog_rv);
        rvResultList.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));
        btnSearch = (Button) rootView.findViewById(R.id.id_fg_search_dialog_btn);
    }

    private void initData() {
        dbHelper = DBHelper.getInstance(getActivity());
    }

    private void initEvent() {
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dialog.dismiss();
    }

    private void doSearch() {
        final String keywords = String.valueOf(svSearch.getQuery());
        if (keywords.length() <= 0)
            return;

        Observable.create(new Observable.OnSubscribe<List<City>>() {
            @Override
            public void call(Subscriber<? super List<City>> subscriber) {
                subscriber.onNext(dbHelper.getSearchList(keywords));
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<City>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("===", e.getMessage() + " ");
                    }

                    @Override
                    public void onNext(List<City> cityList) {
                        mSearchResultList = cityList;
                        SearchListAdapter adapter = new SearchListAdapter(getActivity(), mSearchResultList);
                        adapter.setOnItemClickListener(new SearchListAdapter.onItemClickListener() {
                            @Override
                            public void onClick(View view, int pos) {
                                SearchCallbackListener listener = (SearchCallbackListener) getActivity();
                                listener.onCallbackCity(mSearchResultList.get(pos).getCity());

                                dismiss();
                            }
                        });
                        rvResultList.setAdapter(adapter);
                    }
                });
    }
}
