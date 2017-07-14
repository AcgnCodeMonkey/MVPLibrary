package com.xujl.demo;

import android.content.Context;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

/**
 * Created by xujl on 2017/7/11.
 */

public class AdapterHelper {
    //添加一级标题
    private  Title1Adapter addTitle1 (final String title, Context context) {
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(1);
        linearLayoutHelper.setMarginBottom(20);
        return new Title1Adapter(context, linearLayoutHelper, 1) {
            @Override
            public void onBindViewHolder (MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.tv1.setText(title);
            }
        };

    }

    //添加一级标题
    private  BtnAdapter addItem (final Bean.Bean2 bean2, Context context) {
        //设置线性布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setItemCount(bean2.itemBeanList.size());
        //是否自动扩展
        gridLayoutHelper.setAutoExpand(false);
        return new BtnAdapter(context, gridLayoutHelper, bean2.itemBeanList.size()) {
            @Override
            public void onBindViewHolder (MainViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                final Bean.ItemBean itemBean = bean2.itemBeanList.get(position);
                holder.cb1.setText(itemBean.content);
                holder.cb1.setChecked(itemBean.isChecked);
                holder.cb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View view) {
                        checkedControl(bean2,position,!itemBean.isChecked);
                        notifyDataSetChanged();
                    }
                });
            }
        };

    }

    //添加二级标题
    private  Title2Adapter addTitle2 (final String title, Context context) {
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(1);
        linearLayoutHelper.setMarginBottom(10);
        return new Title2Adapter(context, linearLayoutHelper, 1) {
            @Override
            public void onBindViewHolder (MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.tv1.setText(title);
            }
        };

    }

    public  void addData (List<DelegateAdapter.Adapter> adapters, final List<Bean> list, Context context) {
        final int title1Size = list.size();
        for (int i = 0; i < title1Size; i++) {
            final Bean bean = list.get(i);
            adapters.add(addTitle1(bean.title_1, context));
            for (Bean.Bean2 bean2 : bean.bean2List) {
                adapters.add(addTitle2(bean2.title_2, context));
                adapters.add(addItem(bean2,context));
            }
        }
    }
    private void checkedControl(Bean.Bean2 bean2,int position,boolean change){
        if(!change){
            bean2.itemBeanList.get(position).isChecked = change;
            return;
        }
        final int maxChexked = bean2.maxChexked;
        if(maxChexked == 1){
            for (Bean.ItemBean itemBean : bean2.itemBeanList) {
                if(itemBean.isChecked){
                    itemBean.isChecked = false;
                }
            }
            bean2.itemBeanList.get(position).isChecked = true;
            return;
        }
        int count=0;
        for (Bean.ItemBean itemBean : bean2.itemBeanList) {
            if(itemBean.isChecked){
                count++;
            }
        }
        if(count < maxChexked){
            bean2.itemBeanList.get(position).isChecked = change;
        }
    }
}
