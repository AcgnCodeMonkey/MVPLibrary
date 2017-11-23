package com.xujl.mvpllirary.mvp.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.TextView;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.R;

import java.util.List;

/**
 * Created by xujl on 2017/11/20.
 */

public class TestFragment extends CommonFragmentPresenter {
    TextView mTextView;
    String index;
    private String TAG = "Null";
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mTextView = mRootView.findViewById(R.id.tv);
        index = getArguments().getString("key");
        mTextView.setText("测试数据： " + index);
        TAG = index + "TestFragment---->";
        Log.e(TAG, "onCreateView");
    }

    @Override
    protected void resumePresenter (@NonNull Bundle savedInstanceState) {
        super.resumePresenter(savedInstanceState);
//        mTextView = mRootView.findViewById(R.id.tv);
        mTextView.setText("测试数据： " + savedInstanceState.getString("saved"));
        Log.e(index + "resumePresenter---->", "onCreateView");
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saved","99999");
    }


    @Override
    public void onDestroyView () {
        super.onDestroyView();
        if(mRootView != null){
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public boolean isMVP () {
        return false;
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment;
    }

}
