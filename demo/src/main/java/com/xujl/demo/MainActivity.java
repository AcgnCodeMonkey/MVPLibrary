package com.xujl.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.R.attr.max;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<Bean> mDataList;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //绑定VirtualLayoutManager
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mDataList = new ArrayList<>();
        mRecyclerView.setLayoutManager(layoutManager);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        //初始化数据
        initData(Integer.MAX_VALUE, 1, 1, 1, 1);
        //设置Adapter列表
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        //填充数据
        new AdapterHelper().addData(adapters, mDataList, this);
        //绑定delegateAdapter
        delegateAdapter = new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    private void initData (int max1, int max2, int max3, int max4, int max5) {
       mDataList.clear();
        List<Bean.Bean2> bean2List1 = new ArrayList<>();
        bean2List1.add(new Bean.Bean2("1.屏幕显示（可多选）", max1, Arrays.asList(new Bean.ItemBean(false, 1, "屏幕显示正常")
                , new Bean.ItemBean(false, 2, "内屏进水"), new Bean.ItemBean(false, 3, "有坏点"), new Bean.ItemBean(false, 4, "严重老化"))));

        bean2List1.add(new Bean.Bean2("2.屏幕触摸", max2, Arrays.asList(new Bean.ItemBean(false, 5, "屏幕触摸正常")
                , new Bean.ItemBean(false, 6, "屏幕触摸不正常"))));
        mDataList.add(new Bean("屏幕功能", bean2List1));
        List<Bean.Bean2> bean2List2 = new ArrayList<>();
        bean2List2.add(new Bean.Bean2("3.具体型号", max3, Arrays.asList(new Bean.ItemBean(false, 7, "国行")
                , new Bean.ItemBean(false, 8, "港版"), new Bean.ItemBean(false, 9, "其他"))));
        bean2List2.add(new Bean.Bean2("4.保修情况", max4, Arrays.asList(new Bean.ItemBean(false, 10, "超保")
                , new Bean.ItemBean(false, 11, "未超保"))));
        bean2List2.add(new Bean.Bean2("5.网络类型", max5, Arrays.asList(new Bean.ItemBean(false, 12, "GSM")
                , new Bean.ItemBean(false, 13, "CDMA"))));
        mDataList.add(new Bean("基本情况", bean2List2));
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.btn1:
                initData(1, 1, 1, 1, 1);
                delegateAdapter.notifyDataSetChanged();
                break;
            case R.id.btn2:
                initData(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
                delegateAdapter.notifyDataSetChanged();
                break;
            case R.id.btn3:
                initData(1, Integer.MAX_VALUE, 1, 1, Integer.MAX_VALUE);
                delegateAdapter.notifyDataSetChanged();
                break;
            case R.id.btn4:
                try {
                    final List<Bean.ItemBean> itemBeanList = checkData();
                    Toast.makeText(MainActivity.this, "共选中" + itemBeanList.size() + "条", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;
            default:

                break;

        }
    }

    public List<Bean.ItemBean> checkData () throws Exception {
        List<Bean.ItemBean> list = new ArrayList<>();
        for (Bean bean : mDataList) {
            final List<Bean.Bean2> bean2List = bean.bean2List;
            for (Bean.Bean2 bean2 : bean2List) {
                final List<Bean.ItemBean> itemBeanList = bean2.itemBeanList;
                boolean flag = false;//是否没有选择
                String result = null;
                for (Bean.ItemBean itemBean : itemBeanList) {
                    if (itemBean.isChecked) {
                        flag = true;
                        list.add(itemBean);
                    }
                }
                if (!flag) {
                    result = bean2.title_2 + "未选择";
                    throw new Exception(result);
                }
            }
        }
        return list;
    }
}
