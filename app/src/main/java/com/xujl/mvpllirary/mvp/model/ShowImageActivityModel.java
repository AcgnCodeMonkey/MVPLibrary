package com.xujl.mvpllirary.mvp.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.xujl.applibrary.db.DBUtils;
import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.common.DownloadManagerHelper;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.port.IShowImageActivityModel;
import com.xujl.mvpllirary.util.DownloadFilePath;
import com.xujl.mvpllirary.util.FastBlurUtil;
import com.xujl.mvpllirary.util.IntentKey;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by xujl on 2017/7/8.
 */
public class ShowImageActivityModel extends CommonModel implements IShowImageActivityModel {
    private ImagePassBean mImageEntity;
    private int type;

    @Override
    public boolean imageIsDownload () {
        final ImageBean imageBean = DBUtils.queryForImageId(mImageEntity.getImageId());
        return imageBean != null && imageBean.getType() == ImageBeanType.TYPE_DOWNLOADED;
    }

    @Override
    public boolean imageIsCollection () {
        final ImageBean imageBean = DBUtils.queryForImageId(mImageEntity.getImageId());
        return imageBean != null
                && (imageBean.getType() == ImageBeanType.TYPE_COLLECTION || imageBean.getType() == ImageBeanType.TYPE_ALL);
    }

    @Override
    public void saveToDb (File file, Context context) {
        final Uri imageContentUri = Uri.fromFile(file);
        String path = imageContentUri == null ? "" : imageContentUri.toString();
        DBUtils.insert(creatImageBean(mImageEntity, path, ImageBeanType.TYPE_DOWNLOADED));
    }

    @Override
    public void collectionToDb () {
        final ImageBean imageBean = DBUtils.queryForImageId(mImageEntity.getImageId());
        if (imageBean == null) {
            DBUtils.insert(creatImageBean(mImageEntity, "", ImageBeanType.TYPE_COLLECTION));
            return;
        }
        imageBean.setType(ImageBeanType.TYPE_ALL);
        DBUtils.update(imageBean);
    }

    @Override
    public void deleteCollectionToDb () {
        final ImageBean imageBean = DBUtils.queryForImageId(mImageEntity.getImageId());
        if (imageBean == null) {
            return;
        }
        final int type = imageBean.getType();
        if (type == ImageBeanType.TYPE_ALL) {
            imageBean.setType(ImageBeanType.TYPE_DOWNLOADED);
            DBUtils.update(imageBean);
            return;
        }
        DBUtils.delete(imageBean.getId());
    }

    private ImageBean creatImageBean (ImagePassBean image, String path, int type) {
        final ImageBean imageBean = new ImageBean();
        imageBean.setType(type);
        imageBean.setImageId(image.getImageId());
        imageBean.setImageUrl(image.getImageUrl());
        imageBean.setCreatDate(System.currentTimeMillis());
        imageBean.setImagePath(path);
        return imageBean;
    }

    @Override
    public void savePassData (Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.getExtras() == null) {
            return;
        }
        final Bundle bundle = intent.getExtras();
        mImageEntity = bundle.getParcelable(IntentKey.IMAGE_ENTITY);
        type = bundle.getInt(IntentKey.TYPE);
    }

    @Override
    public int getType () {
        return type;
    }

    @Override
    public String getImageUrl () {
        return mImageEntity.getImageUrl();
    }

    @Override
    public void blurImage (final String url) {
        new Thread(new Runnable() {
            @Override
            public void run () {
                Bitmap bitmap = FastBlurUtil.GetUrlBitmap(url, 20);
                bitmap = FastBlurUtil.getTransparentBitmap(bitmap, 90);
                EventBus.getDefault().post(bitmap);
            }
        }).start();
    }

    @Override
    public void initDownloadHelper (Context context) {
        getModelHelper().addHelper(HelperType.TYPE_ONE, new DownloadManagerHelper());
    }

    public DownloadManagerHelper getDownloadHelper () {
        return getModelHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public boolean deleteImage () {
        final ImageBean imageBean = DBUtils.queryForImageId(mImageEntity.getImageId());
        if (imageBean == null) {
            return false;
        }
        final File file = new File(imageBean.getImagePath());
        final boolean delete = file.delete();
        if (delete) {
            DBUtils.delete(imageBean.getId());
        }
        return delete;
    }

    @Override
    public void downloadImage (Context context, String url, DownloadManagerHelper.DownloadCallback callBack) {
        getDownloadHelper()
                .newTask(context)
                .setDownloadUrl(url)
                .setDownloadFlieName(DownloadFilePath.IMAGE_DOWNLOAD_PATH, mImageEntity.getImageId() + ".jpg")
                .setCallback(callBack)
                .start();
    }
}