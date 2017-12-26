package com.xujl.mvpllirary.mvp.model.port;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.ImagePassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/7/10.
 */
public interface IImageListFragmentModel extends ICommonModel {
    void saveType(Bundle bundle);
    int getType();
    void addData();
    List<ImageBean> getDataList();

    ArrayList<ImagePassBean> getImagePassBeans ();
}