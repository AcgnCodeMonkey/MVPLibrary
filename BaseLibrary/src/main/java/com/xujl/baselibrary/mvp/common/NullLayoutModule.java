package com.xujl.baselibrary.mvp.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import androidx.annotation.IntDef;

/**
 * Created by xujl on 2017/12/15.
 * 空布局配置工具
 */

public class NullLayoutModule {
    /**
     * 加载中
     */
    public static final int LOADING = 0;
    /**
     * 无网络或网络差
     */
    public static final int NO_NETWORK = 1;
    /**
     * 当前页面无数据
     */
    public static final int NO_DATA = 2;
    /**
     * 错误
     */
    public static final int ERROR = 3;

    private FrameLayout mNullLayout;
    private Map<Integer, View> mViewMap;
    private NullViewClickListener mNullViewClickListener;

    public NullLayoutModule (ViewGroup rootView) {
        mViewMap = new HashMap<>();
        mNullLayout = new FrameLayout(rootView.getContext());
        mNullLayout.setBackgroundColor(0xffffffff);
        mNullLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        rootView.addView(mNullLayout);
    }

    /**
     * 设置空布局map，key：空布局类型，
     * 请使用枚举类型
     * value:需要显示的空布局view
     *
     * @param viewMap
     */
    public void setNullView (Map<Integer, View> viewMap) {
        mViewMap.clear();
        mViewMap.putAll(viewMap);
        final Set<Integer> keySet = mViewMap.keySet();
        final Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            final @Code
            int key = iterator.next();
            final View view = mViewMap.get(key);
            //设置监听
            view.setOnClickListener(new ViewClick(key));
            mNullLayout.addView(view);
        }
    }

    /**
     * 显示一个类型的空布局控件
     *
     * @param code
     */
    public void showView (@Code int code) {
        mNullLayout.setVisibility(View.VISIBLE);
        final Set<Integer> keySet = mViewMap.keySet();
        final Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            final @Code
            int key = iterator.next();
            mViewMap.get(key).setVisibility(key == code ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 隐藏某个类型的空布局
     * 调用此方法会直接隐藏整个空布局，
     * 如果要显示其他类型的空布局
     * 请直接调用showView方法
     *
     * @param code
     */
    public void dismissView (@Code int code) {
        mViewMap.get(code).setVisibility(View.GONE);
        mNullLayout.setVisibility(View.GONE);
    }

    public FrameLayout getNullLayout () {
        return mNullLayout;
    }

    public void setNullViewClickListener (NullViewClickListener nullViewClickListener) {
        this.mNullViewClickListener = nullViewClickListener;
    }

    @IntDef({LOADING, NO_NETWORK, NO_DATA, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {

    }

    public interface NullViewClickListener {
        /**
         * 空布局控件点击事件
         *
         * @param view 被点击的控件
         * @param code 判断码
         */
        void nullViewClick (View view, @Code int code);
    }

    private class ViewClick implements View.OnClickListener {
        private @Code
        int code;

        public ViewClick (@Code int code) {
            this.code = code;
        }

        @Override
        public void onClick (View v) {
            if (mNullViewClickListener != null) {
                mNullViewClickListener.nullViewClick(v, code);
            }
        }
    }
}
