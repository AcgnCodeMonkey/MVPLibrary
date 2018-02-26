package com.xujl.mvpllirary.mvp.view;

import android.view.ViewGroup;

import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.IImageListFragmentView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/10.
 */
public class ImageListFragment extends CommonView implements IImageListFragmentView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView, R.id.activity_image_list_refreshLayout
                , R.id.activity_image_list_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(false)
                .setGridLayoutManager(3)
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(refreshLayoutHelper);
        getToolBarModule().showBackBtn(true);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_image_list;
    }

    @Override
    public RefreshRecyclerViewHelper getRefreshRecyclerViewHelper () {
        return getViewHelper().getHelper(RefreshRecyclerViewHelper.class);
    }

    @Override
    public void setAdapter (BaseRecyclerViewAdapter adapter) {
        getRefreshRecyclerViewHelper().setAdapter(adapter);
        adapter.setAnimAndEmptyParentView((ViewGroup) mRootView);
    }

    @Override
    public void setTitle (int type) {
        switch (type) {
            case ImageBeanType.TYPE_DOWNLOADED:
                getToolBarModule().setTitle("我的下载");
                break;
            case ImageBeanType.TYPE_COLLECTION:
                getToolBarModule().setTitle("我的收藏");
                break;
            default:

                break;

        }
    }
}