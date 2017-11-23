package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.HomeImageListAdaper;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.port.IHomeImageListFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IHomeImageListFragmentView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.utilslibrary.system.Log;
import com.xujl.utilslibrary.view.ViewTool;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/9/5.
 * 首页图片列表
 */
public class HomeImageListFragmentPresenter extends CommonFragmentPresenter<IHomeImageListFragmentView, IHomeImageListFragmentModel>
        implements RefreshLayout.RefreshListener {
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentKey.IMAGE_ENTITY, new ImagePassBean(mModel.getDataList().get(position)));
            gotoActivity(ShowImageActivityPresenter.class, bundle);
        }
    };


    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mAdapter = new HomeImageListAdaper(mModel.getDataList());
        mView.setBannerData(mModel.getBannerDataList());
        mView.setAdapter(mAdapter, this);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();

    }

    @Override
    public void onRefresh (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_1);
    }

    @Override
    public void onLoading (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_2);
    }

    @Override
    public void requestSuccess (int mode, String json) {
//        if(mode == CommonModel.MODE_3){
//            Log.e(" CommonModel.MODE_3"," CommonModel.MODE_3");
//            return;
//        }
//        for (int i = 0; i < 20; i++) {
//            requestForGetNoHint(CommonModel.MODE_3);
//        }
        mModel.addData(mode, json);
        mAdapter.cbNotifyDataSetChanged();
        mView.getRefreshRecyclerViewHelper().refreshLoadingComplete();

    }


    @Override
    public void onPause () {
        super.onPause();
        mView.bannerPause();
    }

    @Override
    public void onResume () {
        super.onResume();
        mView.bannerPlay();
    }
}