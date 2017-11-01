package com.xujl.mvpllirary.mvp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.xujl.mvpllirary.R;
import com.xujl.utilslibrary.system.Log;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/10/31.
 */

public class TestActivity extends Activity {
    @Override
    protected void onCreate (
            @Nullable
                    Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        RefreshLayout refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh (com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(3000);
            }

            @Override
            public void onLoadmore (com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(3000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("onSaveInstanceState", "界面被回收了");
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("onSaveInstanceState", "界面重启了");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        Log.e("onSaveInstanceState", "界面销毁了");
    }
}
