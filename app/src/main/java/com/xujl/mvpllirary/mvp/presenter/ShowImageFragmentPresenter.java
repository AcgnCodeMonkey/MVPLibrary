package com.xujl.mvpllirary.mvp.presenter;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.xujl.applibrary.mvp.common.DownloadManagerHelper;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.ShowImagesAdapter;
import com.xujl.mvpllirary.mvp.model.port.IShowImageFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IShowImageFragmentView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.rxlibrary.BaseObservable;
import com.xujl.rxlibrary.BaseObservableEmitter;
import com.xujl.rxlibrary.BaseObserver;
import com.xujl.rxlibrary.RxHelper;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

import java.io.File;

/**
 * Created by xujl on 2017/7/8.
 */
public class ShowImageFragmentPresenter extends CommonFragmentPresenter<IShowImageFragmentView, IShowImageFragmentModel> {
    public static final int RESULT_CODE = 9;
    private BaseRecyclerViewAdapter mAdapter;


    public static ShowImageFragmentPresenter newInstance (Bundle bundle) {

        ShowImageFragmentPresenter fragment = new ShowImageFragmentPresenter();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mModel.savePassData(getArguments());
        mModel.initDownloadHelper(exposeContext());
        mView.setOnScrollListener(mListener);
        mAdapter = new ShowImagesAdapter(mModel.getImages());
        mView.loadType(mModel.getType());
        final int position = getArguments().getInt(IntentKey.POSITION);
        mView.showImage(mAdapter, position);
        changeState(position);
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.activity_showimage_collectionIBtn:
                if (mModel.imageIsCollection()) {
                    mModel.deleteCollectionToDb();
                    mView.showToastMsg(exposeContext(), "取消收藏成功！", CustomToast.SUCCESS);
                } else {
                    mModel.collectionToDb();
                    mView.showToastMsg(exposeContext(), "收藏成功！", CustomToast.SUCCESS);
                }
                mView.changeCollectionImage(mModel.imageIsCollection());
                break;
            case R.id.activity_showimage_deleteIBtn:
                final AlertDialog.Builder builder = new AlertDialog.Builder(exposeContext());
                builder.setTitle("系统提示");
                builder.setMessage("确定要删除此图片？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final boolean b = mModel.deleteImage();
                        if (b) {
                            mView.showToastMsg(exposeContext(), "删除成功！", CustomToast.SUCCESS);
                            setFragmentResult(RESULT_CODE);
                        } else {
                            mView.showToastMsg(exposeContext(), "删除失败！", CustomToast.ERROR);
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.activity_showimage_downloadIBtn:
                if (mModel.imageIsDownload()) {
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(exposeContext());
                    builder2.setTitle("系统提示");
                    builder2.setMessage("此图片已经下载过了，需要重新下载吗？");
                    builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog, int which) {
                            dialog.dismiss();
                            downloadImage();
                        }
                    });
                    builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder2.show();
                    return;
                }
                downloadImage();
                break;
            default:

                break;

        }
    }

    private RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged (RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {
                changeState(mView.getPosition());
            }
        }
    };

    private void changeState (int position) {
        mModel.setNewImage(position);
        RxHelper.onCreate(mRxLife)
                .createNormal(new BaseObservable<Bitmap>() {
                    @Override
                    public void emitAction (BaseObservableEmitter<Bitmap> e) throws Exception {
                        e.onNext(mModel.blurImage(mModel.getImageUrl()));
                    }
                })
                .newThreadToMain()
                .run(new BaseObserver<Bitmap>() {
                    @Override
                    public void onNext (Bitmap bitmap) {
                        super.onNext(bitmap);
                        mView.blurBackground(bitmap);
                        mView.changeCollectionImage(mModel.imageIsCollection());
                    }
                });
    }

    private void downloadImage () {
        mModel.downloadImage(exposeContext(), mModel.getImageUrl(), new DownloadManagerHelper.DownloadCallback() {

            @Override
            protected void pending (BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void progress (BaseDownloadTask task, int soFarBytes, int totalBytes) {
                mView.showToastMsg(exposeContext(), "下载进度：" + soFarBytes / totalBytes * 100 + "%", CustomToast.INFO);
            }

            @Override
            protected void completed (BaseDownloadTask task) {
                mView.showToastMsg(exposeContext(), "保存成功！", CustomToast.SUCCESS);
                mModel.saveToDb(new File(task.getPath()), exposeContext());
            }

            @Override
            protected void paused (BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error (BaseDownloadTask task, Throwable e) {
                mView.showToastMsg(exposeContext(), "下载失败：" + e.toString(), CustomToast.ERROR);
            }

            @Override
            protected void warn (BaseDownloadTask task) {
                mView.showToastMsg(exposeContext(), "下载错误！", CustomToast.WARN);
            }
        });
    }

}