package com.xujl.quotelibrary.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/5/2.
 */

public class RefreshLayout extends SmartRefreshLayout {
    public RefreshLayout (Context context) {
        super(context);
    }

    public RefreshLayout (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setOnRefreshListener (final RefreshListener refreshListener) {
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh (com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                refreshListener.onRefresh((RefreshLayout) refreshlayout);
            }
        });
        setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore (com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                refreshListener.onLoading((RefreshLayout) refreshlayout);
            }
        });
    }

    public void startRefresh () {
        super.autoRefresh();
    }

    public void startLoadMore () {
        super.autoLoadmore();
    }

    public interface RefreshListener {
        void onRefresh (RefreshLayout refreshLayout);

        void onLoading (RefreshLayout refreshLayout);
    }

    public void finishRefreshing () {
        if (!isRefreshing()) {
            return;
        }
        super.finishRefresh();
    }

    public SmartRefreshLayout finishLoadmore () {
        if (!isLoading()) {
            return null;
        }
        return super.finishLoadmore();
    }
}
