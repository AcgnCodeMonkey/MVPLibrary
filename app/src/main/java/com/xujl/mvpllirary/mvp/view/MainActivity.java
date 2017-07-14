package com.xujl.mvpllirary.mvp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.BannerViewHolder;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.widget.RefreshLayout;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.List;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivity extends CommonView implements IMainActivityView {
    private MarqueeView mMarqueeView;
    private MZBannerView<Integer> mMZBanner;
    private View mHeader;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    public void initView (final IBasePresenter presenter) {
        super.initView(presenter);
        getToolBarModule().setTitle("首页");
        mDrawerLayout = (DrawerLayout) mRootView.findViewById(R.id.activity_main_drawerLayout);
        mHeader = LayoutInflater.from(presenter.exposeContext()).inflate(R.layout.part_activity_main_header, null);
        mMZBanner = (MZBannerView) mHeader.findViewById(R.id.activity_main_bannerView);
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView,R.id.activity_main_refreshLayout,R.id.activity_main_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(true)
                .setGridLayoutManager(3)
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(HelperType.TYPE_ONE,refreshLayoutHelper);
        mMarqueeView = (MarqueeView) mHeader.findViewById(R.id.marqueeView);


        getToolBarModule().getActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getToolBarModule().getActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(presenter.exposeActivity(), mDrawerLayout, getToolBarModule().getToolbar(), R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mRootView.findViewById(R.id.part_activity_main_menu_downloadTV).setOnClickListener(presenter);
        mRootView.findViewById(R.id.part_activity_main_menu_collectionTV).setOnClickListener(presenter);
    }


    public class NoticeMF extends MarqueeFactory<TextView, String> {
        private LayoutInflater inflater;

        public NoticeMF(Context mContext) {
            super(mContext);
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public TextView generateMarqueeItemView(String data) {
            TextView mView = (TextView) inflater.inflate(R.layout.notice_item, null);
            mView.setText(data);
            return mView;
        }
    }
    @Override
    public RefreshRecyclerViewHelper getRefreshRecyclerViewHelper () {
        return getViewHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public void setAdapter (BaseRecyclerViewAdapter adapter) {
        adapter.addHeaderView(mHeader);
//        adapter.setAnimAndEmptyParentView((ViewGroup) mRootView);
//        adapter.initFooter(mRootView.getContext());
        getRefreshRecyclerViewHelper().setAdapter(adapter);
    }

    @Override
    public void setTitles (List<String> titles,Context context) {
        MarqueeFactory<TextView, String> marqueeFactory1 = new NoticeMF(context);
        mMarqueeView.setMarqueeFactory(marqueeFactory1);
        mMarqueeView.startFlipping();
        marqueeFactory1.setData(titles);
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
        mMZBanner.start();
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_main;
    }




}