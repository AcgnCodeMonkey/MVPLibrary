package com.xujl.mvpllirary.mvp.view;

import android.view.ViewGroup;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.databinding.FragmentNewsTabBinding;
import com.xujl.mvpllirary.json.NewsPayload;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.INewsTabFragmentView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/9/6.
 */
public class NewsTabFragment extends CommonView implements INewsTabFragmentView {
    private FragmentNewsTabBinding mBinding;

    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mBinding = getDataBinding();
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView, R.id.fragment_news_tab_refreshLayout
                , R.id.fragment_news_tab_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(true)
                .setLinearLayoutManager()
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(HelperType.TYPE_ONE, refreshLayoutHelper);
        mBinding.setTest(new NewsPayload("点击刷新"));
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_news_tab;
    }

    @Override
    public boolean enableDataBinding () {
        return true;
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