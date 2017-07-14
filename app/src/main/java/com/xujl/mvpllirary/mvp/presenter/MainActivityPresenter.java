package com.xujl.mvpllirary.mvp.presenter;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.HomeAdaper;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.MainActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.mvpllirary.mvp.view.MainActivity;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivityPresenter extends CommonActivityPresenter<IMainActivityView, IMainActivityModel> implements RefreshLayout.RefreshListener {
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentKey.IMAGE_ENTITY, new ImagePassBean(mModel.getDataList().get(position)));
            gotoActivity(ShowImageActivityPresenter.class,bundle);
        }
    };

    @Override
    protected Class<? extends IMainActivityView> getViewClassType () {
        return MainActivity.class;
    }

    @Override
    protected Class<? extends IMainActivityModel> getModelClassType () {
        return MainActivityModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mAdapter = new HomeAdaper(mModel.getDataList());
        mView.setTitles(mModel.getTitles(),exposeContext());
        mView.setBannerData(mModel.getBannerDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch(v.getId()){
            case  R.id.part_activity_main_menu_downloadTV:
                final Bundle bundle = new Bundle();
                bundle.putInt(IntentKey.TYPE, ImageBeanType.TYPE_DOWNLOADED);
                gotoActivity(ImageListActivityPresenter.class,bundle);
            break;
            case  R.id.part_activity_main_menu_collectionTV:
                final Bundle bundle2 = new Bundle();
                bundle2.putInt(IntentKey.TYPE, ImageBeanType.TYPE_COLLECTION);
                gotoActivity(ImageListActivityPresenter.class,bundle2);
                break;
            default:

            break;

        }
    }

    @Override
    public void onRefresh (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_1);
    }

    @Override
    public void requestSuccess (int mode, String json) {
        mModel.addData(mode,json);
        mAdapter.cbNotifyDataSetChanged();
        mView.getRefreshRecyclerViewHelper().refreshLoadingComplete();
    }

    @Override
    public void onLoading (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_2);
    }

    @Override
    protected String[] needPermissions () {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    protected void onPause () {
        super.onPause();
        mView.bannerPause();
    }

    @Override
    protected void onResume () {
        super.onResume();
        mView.bannerPlay();
    }
}