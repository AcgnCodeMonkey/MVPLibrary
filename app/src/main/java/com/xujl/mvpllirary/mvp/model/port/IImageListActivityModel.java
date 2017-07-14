package com.xujl.mvpllirary.mvp.model.port;

import android.content.Intent;

import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.port.ICommonModel;

import java.util.List;

/**
 * Created by xujl on 2017/7/10.
 */
public interface IImageListActivityModel extends ICommonModel {
    void saveType(Intent intent);
    int getType();
    void addData();
    List<ImageBean> getDataList();
}