package com.xujl.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Created by xujl on 2017/7/11.
 */

public class BtnAdapter extends DelegateAdapter.Adapter<BtnAdapter.MainViewHolder> {
    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    public BtnAdapter (Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public BtnAdapter (Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.btn,parent,false));
    }




    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        holder.cb1.setText(Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        return count;
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {
        public CheckBox cb1;
        public MainViewHolder(View itemView) {
            super(itemView);
            cb1 = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
