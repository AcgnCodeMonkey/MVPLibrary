package com.xujl.mvpllirary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xujl.mvpllirary.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by xujl on 2017/7/8.
 */

public class BannerViewHolder implements MZViewHolder<Integer> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner,null);
        mImageView = (ImageView) view.findViewById(R.id.item_banner_imageView);
        return view;
    }

    @Override
    public void onBind(Context context, int position, Integer data) {
        // 数据绑定
        mImageView.setImageResource(data);
    }
}
