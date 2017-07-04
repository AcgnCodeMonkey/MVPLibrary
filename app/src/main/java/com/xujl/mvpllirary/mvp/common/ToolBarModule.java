package com.xujl.mvpllirary.mvp.common;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;
import com.xujl.mvpllirary.R;

/**
 * Created by xujl on 2017/7/4.
 */

public class ToolBarModule extends BaseToolBarModule {
    private TextView mTitleTV;
    private ImageButton mLeftIB;
    private ImageButton mRightIB;
    public ToolBarModule (Activity activity, int layoutId) {
        super(activity, layoutId);
        mTitleTV = (TextView) getToolbar().findViewById(R.id.toolbar_layout_titleTV);
        mLeftIB = (ImageButton) getToolbar().findViewById(R.id.toolbar_layout_leftImageBtn);
        mRightIB = (ImageButton) getToolbar().findViewById(R.id.toolbar_layout_rightImageBtn);
    }

    @Override
    public void initSetting (BaseActivityPresenter presenter) {
        super.initSetting(presenter);
        getActionBar().setDisplayShowTitleEnabled(false);
        getToolbar().setTitle("");
    }

    @Override
    protected int getToolBarId () {
        return R.id.toolbar;
    }

    @Override
    protected int getToolBarLayoutId () {
        return R.layout.toolbar_layout;
    }


    public void setTitle(String title){
        mTitleTV.setText(title);
    }
}
