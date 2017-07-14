package com.xujl.mvpllirary.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.TestAdapter;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/7/13.
 */

public class RefreshTest extends Activity {
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    private BaseRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.activity_main_refreshLayout);
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("" + i);
        }
        mAdapter = new TestAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh (RefreshLayout refreshlayout) {
                mRefreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore (RefreshLayout refreshlayout) {
                mRefreshLayout.finishLoadmore(1000);
            }
        });
    }
}
