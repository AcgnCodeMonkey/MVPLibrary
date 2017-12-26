package com.xujl.applibrary.mvp.common;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xujl.applibrary.R;
import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.common.LayoutConfig;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public class ToolBarModule extends BaseToolBarModule {
    private TextView mTitleTV;
    private ImageButton mLeftIB;
    private ImageButton mRightIB;
    private IBasePresenter mPresenter;

    /**
     * 使用此构造器会根据子类返回的toolBar的布局id自动创建toolBar并和内容布局拼接
     * 到一起
     *
     * @param activity
     * @param layoutId activity布局id
     * @param config
     */
    public ToolBarModule (Activity activity, int layoutId, LayoutConfig config) {
        super(activity, layoutId, config);
    }


    @Override
    public void initSetting (IBasePresenter presenter) {
        super.initSetting(presenter);
        mTitleTV = (TextView) getToolbar().findViewById(R.id.toolbar_layout_titleTV);
        mLeftIB = (ImageButton) getToolbar().findViewById(R.id.toolbar_layout_leftImageBtn);
        mRightIB = (ImageButton) getToolbar().findViewById(R.id.toolbar_layout_rightImageBtn);
        mPresenter = presenter;
        if (presenter instanceof Activity) {
            getActionBar().setDisplayShowTitleEnabled(false);
            getToolbar().setTitle("");
        }
    }

    public void showBackBtn (boolean isShow) {

        if (!(mPresenter instanceof Activity)) {
            return;
        }
        getActionBar().setDisplayHomeAsUpEnabled(isShow);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mPresenter.exit();
            }
        });
    }

    @Override
    protected int getToolBarId () {
        return R.id.toolbar;
    }

    @Override
    protected int getToolBarLayoutId () {
        return R.layout.toolbar_layout;
    }

    public ImageButton getLeftIB () {
        return mLeftIB;
    }

    public ImageButton getRightIB () {
        return mRightIB;
    }

    public void setTitle (String title) {
        mTitleTV.setText(title);
    }

}
