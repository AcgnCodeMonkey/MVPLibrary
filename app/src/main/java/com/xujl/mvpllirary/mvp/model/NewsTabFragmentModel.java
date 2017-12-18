package com.xujl.mvpllirary.mvp.model;

import android.os.Bundle;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.datalibrary.network.ApiName;
import com.xujl.mvpllirary.json.HomeNewsPayload;
import com.xujl.mvpllirary.mvp.model.port.INewsTabFragmentModel;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.utilslibrary.data.ParamsMapTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xujl on 2017/9/6.
 */
public class NewsTabFragmentModel extends CommonModel implements INewsTabFragmentModel {
    private List<HomeNewsPayload.News> mDataList;
    private String apiNameType;//资讯类型
    private int nowPage = 1;


    @Override
    public void initModel (IBasePresenter presenter) {
        super.initModel(presenter);
        mDataList = new ArrayList<>();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void initType (Bundle bundle) {
        if (bundle != null) {
            apiNameType  = bundle.getString(IntentKey.TYPE);
        }
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
                api = apiNameType + "/12/" + 1;
                resetBaseUrl(ApiName.BASE_1);
                break;
            default:
                api = apiNameType + "/12/" + nowPage;
                break;

        }
        return api;
    }
}