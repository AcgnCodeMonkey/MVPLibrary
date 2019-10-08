package com.xujl.mvpllirary.mvp.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageButton;

import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.view.port.IShowImageFragmentView;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by xujl on 2017/7/8.
 */
public class ShowImageFragment extends CommonView implements IShowImageFragmentView {
    private RecyclerView mRecyclerView;
    private ImageButton mCollectionIBtn;
    private ImageButton mDeleteIBtn;
    private ImageButton mDownloadIBtn;

    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mDownloadIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_downloadIBtn);
        mDownloadIBtn.setOnClickListener(presenter);
        mCollectionIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_collectionIBtn);
        mCollectionIBtn.setOnClickListener(presenter);
        mDeleteIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_deleteIBtn);
        mDeleteIBtn.setOnClickListener(presenter);
        mRecyclerView = findView(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(presenter.exposeContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        // 将SnapHelper attach 到RecyclrView
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_showimage;
    }

    @Override
    public boolean enableToolBar () {
        return false;
    }

    @Override
    public void showImage (BaseRecyclerViewAdapter adapter, int anInt) {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.scrollToPosition(anInt);
    }

    @Override
    public void blurBackground (Bitmap bitmap) {
        final View rootView = mRootView.findViewById(R.id.activity_showimage_rootView);
        rootView.setBackground(new BitmapDrawable(mRootView.getResources(), bitmap));
    }

    @Override
    public void changeCollectionImage (boolean isCollection) {
        if (isCollection) {
            mCollectionIBtn.setImageResource(R.drawable.showimage_nocollection);
        } else {
            mCollectionIBtn.setImageResource(R.drawable.showimage_collection);
        }
    }

    @Override
    public void setOnScrollListener (RecyclerView.OnScrollListener listener) {
        mRecyclerView.setOnScrollListener(listener);
    }

    @Override
    public void loadType (int type) {
        switch (type) {
            case ImageBeanType.TYPE_COLLECTION:
                mDownloadIBtn.setVisibility(View.VISIBLE);
                mDeleteIBtn.setVisibility(View.GONE);
                break;
            case ImageBeanType.TYPE_DOWNLOADED:
                mDownloadIBtn.setVisibility(View.GONE);
                mDeleteIBtn.setVisibility(View.VISIBLE);
                break;
            case ImageBeanType.TYPE_ALL:
                mDownloadIBtn.setVisibility(View.GONE);
                mDeleteIBtn.setVisibility(View.VISIBLE);
                break;
            default:
                mDownloadIBtn.setVisibility(View.VISIBLE);
                mDeleteIBtn.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public int getPosition () {
        return ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }
}