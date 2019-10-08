package com.xujl.mvpllirary.mvp.view.port;

import android.graphics.Bitmap;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by xujl on 2017/7/8.
 */
public interface IShowImageFragmentView extends ICommonView {
    void showImage (BaseRecyclerViewAdapter adapter, int anInt);
    void blurBackground(Bitmap bitmap);//高斯模糊背景
    void changeCollectionImage(boolean isCollection);//根据是否收藏变换按钮图片
    void setOnScrollListener(RecyclerView.OnScrollListener listener);
    void loadType (int type);

    int getPosition ();
}