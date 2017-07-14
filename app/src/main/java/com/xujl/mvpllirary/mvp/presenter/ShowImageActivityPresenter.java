package com.xujl.mvpllirary.mvp.presenter;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.xujl.applibrary.mvp.common.DownloadManagerHelper;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.model.ShowImageActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IShowImageActivityModel;
import com.xujl.mvpllirary.mvp.view.ShowImageActivity;
import com.xujl.mvpllirary.mvp.view.port.IShowImageActivityView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * Created by xujl on 2017/7/8.
 */
public class ShowImageActivityPresenter extends CommonActivityPresenter<IShowImageActivityView, IShowImageActivityModel> {
    @Override
    protected Class<? extends IShowImageActivityView> getViewClassType () {
        return ShowImageActivity.class;
    }

    @Override
    protected Class<? extends IShowImageActivityModel> getModelClassType () {
        return ShowImageActivityModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mModel.savePassData(getIntent());
        mModel.initDownloadHelper(exposeContext());
        mModel.blurImage(mModel.getImageUrl());
        mView.loadType(mModel.getType());
        mView.showImage(mModel.getImageUrl());
        mView.changeCollectionImage(mModel.imageIsCollection());
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.activity_showimage_collectionIBtn:
                if(mModel.imageIsCollection()){
                    mModel.deleteCollectionToDb();
                    mView.showToastMsg(exposeContext(), "取消收藏成功！", CustomToast.SUCCESS);
                }else{
                    mModel.collectionToDb();
                    mView.showToastMsg(exposeContext(), "收藏成功！", CustomToast.SUCCESS);
                }
                mView.changeCollectionImage(mModel.imageIsCollection());
                break;
            case R.id.activity_showimage_deleteIBtn:

                break;
            case R.id.activity_showimage_downloadIBtn:
                if (mModel.imageIsDownload()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("系统提示");
                    builder.setMessage("此图片已经下载过了，需要重新下载吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog, int which) {
                            dialog.dismiss();
                            downloadImage();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    return;
                }
                downloadImage();
                break;
            default:

                break;

        }
    }


    private void downloadImage () {
        mModel.downloadImage(exposeContext(), mModel.getImageUrl(), new DownloadManagerHelper.DownloadCallback() {
            @Override
            public void complete (File file) {
                mView.showToastMsg(exposeContext(), "保存成功！", CustomToast.SUCCESS);
                mModel.saveToDb(file, exposeContext());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void blurBackground (Bitmap bitmap) {
        mView.blurBackground(bitmap);
    }

    @Override
    protected void onDestroy () {
        EventBus.getDefault().unregister(this);
        mModel.getDownloadHelper().unRegisterReceiver(exposeContext());
        super.onDestroy();
    }
}