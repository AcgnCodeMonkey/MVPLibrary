package com.xujl.mvpllirary.mvp.model;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.datalibrary.network.ApiName;
import com.xujl.mvpllirary.json.HomeNewsPayload;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.utilslibrary.data.ParamsMapTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xujl on 2017/9/6.
 */
public class HomeNewsFragmentModel extends CommonModel implements IHomeNewsFragmentModel {
    private List<HomeNewsPayload.News> mDataList;
    private int nowPage = 1;

    public HomeNewsFragmentModel () {
        mDataList = new ArrayList<>();
    }

    @Override
    public void addData (int mode, String json) {
        if (mode == MODE_1) {
            mDataList.clear();
            nowPage = 1;
        }
        final HomeNewsPayload homePayload = fromJson(json, HomeNewsPayload.class);
        mDataList.addAll(homePayload.results);
        nowPage++;
    }

    @Override
    public List<HomeNewsPayload.News> getDataList () {
        return mDataList;
    }
    @Override
    protected void addParams (int mode, Map<String, Object> params, ParamsMapTool paramsMapTool) {
        switch (mode) {
            default:
                break;

        }
    }

    @Override
    protected String getApiName (int mode) {
        String api = null;
        switch (mode) {
            case MODE_1:
                api = ApiName.HOME_NEWS + "/12/" + nowPage;
                resetBaseUrl(ApiName.BASE_1);
                break;
            default:
                api = ApiName.HOME_NEWS + "/12/" + nowPage;
                break;

        }
        return api;
    }
}