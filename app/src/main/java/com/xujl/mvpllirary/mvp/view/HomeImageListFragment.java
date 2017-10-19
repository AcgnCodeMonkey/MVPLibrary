package com.xujl.mvpllirary.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.BannerViewHolder;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.IHomeImageListFragmentView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.List;

/**
 * Created by xujl on 2017/9/5.
 */
public class HomeImageListFragment extends CommonView implements IHomeImageListFragmentView {
    private MZBannerView<Integer> mMZBanner;
    private View mHeader;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mHeader = LayoutInflater.from(presenter.exposeContext()).inflate(R.layout.part_activity_main_header, null);
        mMZBanner = (MZBannerView) mHeader.findViewById(R.id.activity_main_bannerView);

    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_home_imagelist;
    }

    @Override
    public RefreshRecyclerViewHelper getRefreshRecyclerViewHelper () {
        return getViewHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public void setAdapter (BaseRecyclerViewAdapter adapter,IBasePresenter presenter) {
        adapter.addHeaderView(mHeader);
        adapter.setAnimAndEmptyParentView((ViewGroup) mRootView);
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView,R.id.fragment_home_imagelist_refreshLayout
                ,R.id.fragment_home_imagelist_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(true)
                .setGridLayoutManager(3)
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(HelperType.TYPE_ONE,refreshLayoutHelper);

        getRefreshRecyclerViewHelper().setAdapter(adapter);
    }


    @Override
    public void setBannerData (List<Integer> list) {
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    @Override
    public void bannerPause () {
        mMZBanner.pause();
    }

    @Override
    public void bannerPlay () {
//        mMZBanner.start();
    }
}