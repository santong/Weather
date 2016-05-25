package me.santong.weather.framework;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by santong.
 * At 15/10/15 11:45
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected LayoutInflater inflater;
    protected int rootLayoutID;
    protected FragmentActivity mActivity;

    public BaseFragment(int rootLayoutID) {
        super();
        this.rootLayoutID = rootLayoutID;
    }

    private BaseFragment() {
    }

    public void onSelectCurrent() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        rootView = inflater.inflate(rootLayoutID, container, false);
        viewDidLoad();
        return rootView;
    }

    abstract protected void viewDidLoad();


    public void log(String msg) {
        Log.e("WM", msg);
    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }

    public int pxToDp(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        int dp = (int) (px / scale + 0.5f);
        return dp;
    }

    public void setViewSize(View view, int width, int height) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p != null) {
            p.width = width;
            p.height = height;
        }
        view.setLayoutParams(p);
    }

    public void setViewWidth(View view, int width) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p != null) {
            p.width = width;
        }
        view.setLayoutParams(p);
    }

    public void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p != null) {
            p.height = height;
        }
        view.setLayoutParams(p);
    }

    protected void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    protected void showView(int id) {
        showView(findViewById(id));
    }

    protected void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    protected void hideView(int id) {
        hideView(findViewById(id));
    }

    protected void toggleEnable(int id) {
        View v = findViewById(id);
        if (v == null) return;
        v.setEnabled(!v.isEnabled());
    }

    protected void toggleVisible(int id) {
        toggleVisible(id, false);
    }

    protected void toggleVisible(int id, boolean invisible) {
        View v = findViewById(id);
        if (v == null) return;
        if (v.getVisibility() == View.VISIBLE) {
            if (invisible)
                v.setVisibility(View.INVISIBLE);
            else
                v.setVisibility(View.GONE);
        } else {
            v.setVisibility(View.VISIBLE);
        }
    }

    protected View findViewById(int id) {
        return rootView.findViewById(id);
    }

    protected String getText(EditText editor) {
        if (editor == null) return null;
        if (editor.getText() == null) return null;
        return editor.getText().toString();
    }

    protected String getText(TextView textView) {
        if (textView == null) return null;
        if (textView.getText() == null) return null;
        return textView.getText().toString();
    }

    protected int marginLeft(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams rParms = (RelativeLayout.LayoutParams) params;
                return rParms.leftMargin;
            }
        }
        return 0;
    }

    protected int marginRight(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams rParms = (RelativeLayout.LayoutParams) params;
                return rParms.rightMargin;
            }
        }
        return 0;
    }

    protected int marginTop(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams rParms = (RelativeLayout.LayoutParams) params;
                return rParms.topMargin;
            }
        }
        return 0;
    }

    protected int marginBottom(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams rParms = (RelativeLayout.LayoutParams) params;
                return rParms.bottomMargin;
            }
        }
        return 0;
    }

    protected void setPaddingLeft(View view, int paddingLeft) {
        view.setPadding(paddingLeft, view.getPaddingTop(),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    protected void setPaddingRight(View view, int paddingRight) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                paddingRight, view.getPaddingBottom());
    }

    protected void setPaddingTop(View view, int paddingTop) {
        view.setPadding(view.getPaddingLeft(), paddingTop,
                view.getPaddingRight(), view.getPaddingBottom());
    }

    protected void setPaddingBottom(View view, int paddingBottom) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                view.getPaddingRight(), paddingBottom);
    }

    protected ProgressDialog mProgressDialog;

    /**
     * 展示对话框
     */
    public void showProgressDialog() {
        initMActivity();
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        mProgressDialog = ProgressDialog.show(mActivity, null, "请稍候...", true);
        mProgressDialog.setCancelable(false);
    }

    /**
     * 展示对话框并设置对话框监听
     *
     * @param cancelListener
     */
    public void showProgressDialog(DialogInterface.OnCancelListener cancelListener) {
        initMActivity();
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage("请稍候...");
        mProgressDialog.setOnCancelListener(cancelListener);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    /**
     * 展示对对话框并设置文字
     *
     * @param message
     */
    public void showProgressDialog(int message) {
        initMActivity();
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage(getString(message));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * 隐藏对对话框
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * 初始化Activity
     */
    public void initMActivity() {
        if (mActivity == null)
            mActivity = getActivity();
    }
}
