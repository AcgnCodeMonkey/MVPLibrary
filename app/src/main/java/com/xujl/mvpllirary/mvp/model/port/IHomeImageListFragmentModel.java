package com.xujl.mvpllirary.mvp.model.port;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.HomeImagelistPayload;

import java.util.List;

/**
 * Created by xujl on 2017/9/5.
 */
public interface IHomeImageListFragmentModel extends ICommonModel {
    void addData(int mode,String json);
    List<HomeImagelistPayload.Images> getDataList();
    List<Integer> getBannerDataList();
}