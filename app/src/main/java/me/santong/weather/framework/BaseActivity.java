package me.santong.weather.framework;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/**
 * Created by santong.
 * At 15/10/14 15:49
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = BaseActivity.this;

        setContentView(getLayoutID());

        initActivity();
    }

    abstract protected int getLayoutID();

    abstract protected void initActivity();

    public void logi(String msg) {
        Log.i("WM" + ((Object) this).getClass().getSimpleName(), msg);
    }

    public void logd(String msg) {
        Log.d("WM" + ((Object) this).getClass().getSimpleName(), msg);
    }

    public void loge(String msg) {
        Log.e("WM" + ((Object) this).getClass().getSimpleName(), msg);
    }

    public void logw(String msg) {
        Log.w("WM" + ((Object) this).getClass().getSimpleName(), msg);
    }

    public void pushActivity(Class<? extends BaseActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void pushActivity(Class<? extends BaseActivity> activityClass, Uri data) {
        Intent intent = new Intent(this, activityClass);
        intent.setData(data);
        startActivity(intent);
    }

    protected void showView(View view) {
        if (view != null)
            view.setVisibility(View.VISIBLE);
    }

    protected void showView(int id) {
        showView(findViewById(id));
    }

    protected void hideView(View view) {
        if (view != null)
            view.setVisibility(View.GONE);
    }

    protected void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(mContext, null, "请稍候...", true);
        progressDialog.setCancelable(false);
    }

    protected void showProgressDialog(boolean cancelable) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(mContext, null, "请稍候...", true);
        progressDialog.setCancelable(cancelable);
    }

    /**
     * 展示对话框并设置对话框监听
     */
    protected void showProgressDialog(DialogInterface.OnCancelListener cancelListener) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("请稍候...");
        progressDialog.setOnCancelListener(cancelListener);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * 展示对对话框并设置文字
     */
    protected void showProgressDialog(int message) {
        if (progressDialog != null)
            progressDialog.dismiss();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(getString(message));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected boolean isEmpty(String str) {
        return null != str && !TextUtils.isEmpty(str);
    }


}
