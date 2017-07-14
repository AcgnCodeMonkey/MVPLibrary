package com.xujl.mvpllirary.mvp.model.port;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.HomePayload;

import java.util.List;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainActivityModel extends ICommonModel {
    void addData(int mode,String json);
    List<HomePayload.Images> getDataList();
    List<String> getTitles();
    List<Integer> getBannerDataList();
}