package com.xujl.mvpllirary.mvp.model.port;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.HomeNewsPayload;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */
public interface IHomeNewsFragmentModel extends ICommonModel {
    void addData(int mode,String json);
    List<HomeNewsPayload.News> getDataList();
}