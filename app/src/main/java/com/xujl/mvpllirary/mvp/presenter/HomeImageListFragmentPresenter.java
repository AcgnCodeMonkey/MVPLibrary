package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
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
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();
        String json = "{\n" +
                "    title: \"老干妈也能替代口红？烈焰红唇竟完爆大牌！\",\n" +
                "    duration: \"144\",\n" +
                "    episode: '0',\n" +
                "    recordHistory: !(COVER_INFO.id&&COVER_INFO.positive_trailer1&&02),\n" +
                "    vid: \"c0554zqiqhd\",\n" +
                "    piantou: \"0\",\n" +
                "    pianwei: \"0\",\n" +
                "    showMark: \"0\",\n" +
                "    showBullet: false,\n" +
                "    showImageBullet: false,\n" +
                "    openBulletDefault: false,\n" +
                "    isNeedPay: false,\n" +
                "    isTrailer: 0,\n" +
                "    positive_trailer: 0,\n" +
                "    singlePrice: \"undefined\",\n" +
                "    vipPrice: \"undefined\",\n" +
                "    tryTime: \"null\",\n" +
                "    isTrailer: 0,\n" +
                "    resolution: 39,\n" +
                "    type: \"25\"\n" +
                "}\n";
        int startIndex = json.indexOf("vid:") + "vid:".length();
        int endIndex = json.indexOf(",", startIndex);
        final String substring = json.substring(startIndex + 2, endIndex - 1);
        Log.e("result---------", substring);
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