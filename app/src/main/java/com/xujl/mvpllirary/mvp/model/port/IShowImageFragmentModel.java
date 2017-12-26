package com.xujl.mvpllirary.mvp.model.port;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.common.DownloadManagerHelper;
import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.ImagePassBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/7/8.
 */
public interface IShowImageFragmentModel extends ICommonModel {
    boolean imageIsDownload ();//图片是否已经下载过

    boolean imageIsCollection ();//图片是否已经收藏

    void saveToDb (File file, Context context);//保存图片到数据库

    void collectionToDb ();//收藏图片到数据库

    void deleteCollectionToDb ();//取消收藏图片到数据库

    void savePassData (Bundle bundle);//保存传递数据

    int getType();

    String getImageUrl ();

    Bitmap blurImage (String url);

    void initDownloadHelper (Context context);

    void downloadImage (Context context, String url, DownloadManagerHelper.DownloadCallback callBack);

    DownloadManagerHelper getDownloadHelper ();

    boolean deleteImage();

    ArrayList<ImagePassBean> getImages ();

    void setNewImage (int position);
}