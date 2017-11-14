package com.xujl.myapplication;

import android.content.Context;
import android.view.View;

import com.xujl.applibrary.mvp.port.ICommonView;

/**
 * Created by xujl on 2017/11/13.
 */
public interface ITestFragmentView extends ICommonView {
    void changeBG(int colorRes);
    void showWindow(View view, Context context);
}