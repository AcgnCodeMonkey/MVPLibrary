package com.xujl.utilslibrary.system;

import android.content.Context;

public class DensityUtil {
    public static Context mContext;
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dip2px (float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip (float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp (float pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     *
     * @return
     */
    public static int sp2px (float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
