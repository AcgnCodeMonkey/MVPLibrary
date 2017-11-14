package com.xujl.mvpllirary.mvp.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.view.port.IShowImageActivityView;
import com.xujl.widgetlibrary.util.ImageLoader;

/**
 * Created by xujl on 2017/7/8.
 */
public class ShowImageActivity extends CommonView implements IShowImageActivityView {
    private ImageView mImageView;
    private ImageButton mCollectionIBtn;
    private ImageButton mDeleteIBtn;
    private ImageButton mDownloadIBtn;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mImageView = (ImageView) mRootView.findViewById(R.id.activity_showimage_imageView);
        mDownloadIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_downloadIBtn);
        mDownloadIBtn.setOnClickListener(presenter);
        mCollectionIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_collectionIBtn);
        mCollectionIBtn.setOnClickListener(presenter);
        mDeleteIBtn = (ImageButton) mRootView.findViewById(R.id.activity_showimage_deleteIBtn);
        mDeleteIBtn.setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_showimage;
    }

    @Override
    public boolean enableToolBar () {
        return false;
    }

    @Override
    public void showImage (String url) {
        ImageLoader.loadImage(url,mImageView);
    }

    @Override
    public void blurBackground (Bitmap bitmap) {
        final View rootView = mRootView.findViewById(R.id.activity_showimage_rootView);
        rootView.setBackground(new BitmapDrawable(mRootView.getResources(),bitmap));
    }

    @Override
    public void changeCollectionImage (boolean isCollection) {
        if(isCollection){
            mCollectionIBtn.setImageResource(R.drawable.showimage_nocollection);
        }else{
            mCollectionIBtn.setImageResource(R.drawable.showimage_collection);
        }
    }

    @Override
    public void loadType (int type) {
        switch(type){
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
}