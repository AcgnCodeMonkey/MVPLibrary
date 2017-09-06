package com.xujl.mvpllirary.mvp.view;

import android.view.ViewGroup;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/9/6.
 */
public class HomeNewsFragment extends CommonView implements IHomeNewsFragmentView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView,R.id.fragment_home_news_refreshLayout
                ,R.id.fragment_home_news_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(true)
                .setLinearLayoutManager()
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(HelperType.TYPE_ONE,refreshLayoutHelper);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_home_news;
    }
    @Override
    public RefreshRecyclerViewHelper getRefreshRecyclerViewHelper () {
        return getViewHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public void setAdapter (BaseRecyclerViewAdapter adapter) {
        adapter.setAnimAndEmptyParentView((ViewGroup) mRootView);
        getRefreshRecyclerViewHelper().setAdapter(adapter);
    }
}