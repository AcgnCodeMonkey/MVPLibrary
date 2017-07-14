package com.xujl.mvpllirary.mvp.view;

import android.view.ViewGroup;

import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.mvpllirary.mvp.view.port.IImageListActivityView;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/10.
 */
public class ImageListActivity extends CommonView implements IImageListActivityView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        RefreshRecyclerViewHelper refreshLayoutHelper = new RefreshRecyclerViewHelper(mRootView, R.id.activity_image_list_refreshLayout
                , R.id.activity_image_list_recyclerView);
        refreshLayoutHelper.initRefreshLayout()
                .enableLoading(false)
                .setGridLayoutManager(3)
                .setOnRefreshListener((RefreshLayout.RefreshListener) presenter);
        getViewHelper().addHelper(HelperType.TYPE_ONE, refreshLayoutHelper);
        getToolBarModule().showBackBtn(true);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_image_list;
    }

    @Override
    public RefreshRecyclerViewHelper getRefreshRecyclerViewHelper () {
        return getViewHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public void setAdapter (BaseRecyclerViewAdapter adapter) {
        getRefreshRecyclerViewHelper().setAdapter(adapter);
        adapter.setAnimAndEmptyParentView((ViewGroup) mRootView);
        adapter.initFooter(mRootView.getContext());
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