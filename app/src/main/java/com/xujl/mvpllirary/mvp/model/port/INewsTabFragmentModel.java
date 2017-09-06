package com.xujl.mvpllirary.mvp.model.port;

import android.os.Bundle;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.HomeNewsPayload;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */
public interface INewsTabFragmentModel extends ICommonModel {
    void addData(int mode,String json);
    void initType(Bundle bundle);
    List<HomeNewsPayload.News> getDataList();
}