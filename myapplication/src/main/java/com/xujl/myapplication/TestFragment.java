package com.xujl.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/11/13.
 */
public class TestFragment extends CommonView implements ITestFragmentView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        findView(R.id.iv1).setOnClickListener(presenter);
        findView(R.id.iv2).setOnClickListener(presenter);
        findView(R.id.iv3).setOnClickListener(presenter);
        findView(R.id.iv4).setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment;
    }

    @Override
    public void changeBG (int colorRes) {
        findView(R.id.root).setBackgroundColor(ContextCompat.getColor(mRootView.getContext(),colorRes));
    }

    @Override
    public void showWindow (View view, Context context) {
        final PopupWindowView window = new PopupWindowView(context);
        window.showUp(view);
    }
}