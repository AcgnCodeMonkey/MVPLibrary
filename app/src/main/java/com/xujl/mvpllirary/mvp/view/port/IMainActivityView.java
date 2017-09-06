package com.xujl.mvpllirary.mvp.view.port;

import android.support.v4.app.FragmentStatePagerAdapter;

import com.xujl.applibrary.mvp.port.ICommonView;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainActivityView extends ICommonView {
    void setAdapter(FragmentStatePagerAdapter adapter);//设置适配器
    void setCurrentItem(int position,String title);//改变标题和页面
}