package com.xujl.widgetlibrary.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.xujl.quotelibrary.R;
import com.xujl.utilslibrary.view.ViewTool;


/**
 * Created by Administrator on 2016/10/10.
 */

public class ImageLoader {
    public static void loadImage ( int res, final ImageView imageView) {
        if (res == 0) {
            imageView.setImageDrawable(null);
            return;
        }
        final DrawableTypeRequest<Integer> typeRequest = Glide.with(imageView.getContext()).load(res);
        typeRequest.error(R.drawable.icon_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //加载中显示的图片
                .placeholder(R.drawable.icon_default)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady (GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                    }
                });
    }
    public static void loadImage ( String url, final ImageView imageView) {
        if (ViewTool.isEmpty(url)) {
            imageView.setImageDrawable(null);
            return;
        }
        final DrawableTypeRequest<String> typeRequest = Glide.with(imageView.getContext()).load(url);
        typeRequest.error(R.drawable.icon_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //加载中显示的图片
                .placeholder(R.drawable.icon_default)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady (GlideDrawable drawable, GlideAnimation anim) {
                        //在这里添加一些图片加载完成的操作
                        super.onResourceReady(drawable, anim);
                    }
                });
    }

    public static void loadAnotherImage(Context context, String url, final ImageView imageView) {
        if (ViewTool.isEmpty(url)) {
            imageView.setImageDrawable(null);
            return;
        }
        final DrawableTypeRequest<String> typeRequest = Glide.with(context).load(url);
        typeRequest.error(R.drawable.icon_error_another)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //加载中显示的图片
                .placeholder(R.drawable.icon_default_another)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady (GlideDrawable drawable, GlideAnimation anim) {
                        //在这里添加一些图片加载完成的操作
                        super.onResourceReady(drawable, anim);
                    }
                });
    }


}
