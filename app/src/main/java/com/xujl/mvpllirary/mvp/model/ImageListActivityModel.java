package com.xujl.mvpllirary.mvp.model;

import android.content.Intent;
import android.os.Bundle;

import com.xujl.applibrary.db.DBUtils;
import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.mvpllirary.mvp.model.port.IImageListActivityModel;
import com.xujl.mvpllirary.util.IntentKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/7/10.
 */
public class ImageListActivityModel extends CommonModel implements IImageListActivityModel {
    private int type;
    private List<ImageBean> mDataList;

    @Override
    public void saveType (Intent intent) {
        if (intent == null) {
            return;
        }
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            type = bundle.getInt(IntentKey.TYPE);
        }
    }

    @Override
    public int getType () {
        return type;
    }

    @Override
    public void addData () {
        mDataList.clear();
        final List<ImageBean> imageBeen = DBUtils.queryForType(type);
        mDataList.addAll(imageBeen);
    }

    @Override
    public List<ImageBean> getDataList () {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }
}