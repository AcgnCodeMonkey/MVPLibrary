package com.xujl.applibrary.window;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.xujl.applibrary.R;
import com.xujl.baselibrary.mvp.port.IBasePresenter;


/**
 * Created by Administrator on 2016/6/16.
 * 加载提示
 */
public class LoadingPopupWindow {
    private IBasePresenter mActivity;
    private PopupWindow mWindow;
    private boolean isLoading;
    private View mBg;
    private View mContentView;

    public LoadingPopupWindow (IBasePresenter activity) {
        this.mActivity = activity;
        mContentView = LayoutInflater.from(mActivity.exposeContext()).inflate(R.layout.window_loading_layout, null);

        mWindow = new PopupWindow(mContentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
//        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(false);
        mWindow.setBackgroundDrawable(new BitmapDrawable());
        mBg = mContentView.findViewById(R.id.progressbar_loading_bg);

    }

    public View getContentView () {
        return mContentView;
    }

    public void show () {
        try {
            mWindow.showAtLocation(mActivity.exposeView().exposeRootView(), Gravity.CENTER, 0, 0);
            isLoading = true;
        } catch (Exception e) {
            //窗口未创建
            e.printStackTrace();
        }

    }

    public void dismiss () {
        try {
            mWindow.dismiss();
            isLoading = false;
        } catch (Exception e) {
            isLoading = false;
            e.printStackTrace();
        }
    }

    public boolean isLoading () {
        return isLoading;
    }

    public void dismissBg () {
        mBg.setBackgroundColor(Color.parseColor("#00000000"));
    }
}
