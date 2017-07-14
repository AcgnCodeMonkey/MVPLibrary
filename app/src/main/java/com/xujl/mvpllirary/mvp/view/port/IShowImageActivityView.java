package com.xujl.mvpllirary.mvp.view.port;

import android.graphics.Bitmap;

import com.xujl.applibrary.mvp.port.ICommonView;

/**
 * Created by xujl on 2017/7/8.
 */
public interface IShowImageActivityView extends ICommonView {
    void showImage(String url);
    void blurBackground(Bitmap bitmap);//高斯模糊背景
    void changeCollectionImage(boolean isCollection);//根据是否收藏变换按钮图片

    void loadType (int type);
}