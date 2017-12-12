package com.xujl.applibrary.mvp.common;

import android.content.Context;

import com.xujl.applibrary.util.AppApplication;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.applibrary.window.LoadingPopupWindow;
import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public class CommonViewHelper extends BaseViewHelper {
    private LoadingPopupWindow mLoadingPopupWindow;

    public CommonViewHelper (IBasePresenter presenter) {
        mLoadingPopupWindow = new LoadingPopupWindow(presenter);
    }

    public void showToastMsg (Context context, String msg, int code) {
        CustomToast.showToast(context, msg, code);
    }

    public void showToastMsg (Context context, String msg, int code, int time) {
        showToastMsg(context, msg, code);
    }
    public void toast(String msg, int code){
        CustomToast.showToast(AppApplication.getInstance(),msg,code);
    }
    public void toast(String msg, int code, int time){
        toast(msg, code);
    }
    public void showLoading () {
        mLoadingPopupWindow.show();
    }

    public void dismissLoading () {
        mLoadingPopupWindow.dismiss();
    }

    public boolean isLoading () {
        return mLoadingPopupWindow.isLoading();
    }
}
