package com.xujl.mvpllirary.mvp.model;

import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.datalibrary.network.ApiName;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomeImagelistPayload;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.port.IHomeImageListFragmentModel;
import com.xujl.utilslibrary.data.ListUtils;
import com.xujl.utilslibrary.data.ParamsMapTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xujl on 2017/9/5.
 */
public class HomeImageListFragmentModel extends CommonModel implements IHomeImageListFragmentModel {
    private List<HomeImagelistPayload.Images> mDataList;
    private final List<Integer> mBannerList = new ArrayList<>();
    private int nowPage = 1;

    public HomeImageListFragmentModel () {
        mDataList = new ArrayList<>();
    }

    @Override
    public void addData (int mode, String json) {
        if (mode == MODE_1) {
            mDataList.clear();
            nowPage = 1;
        }
        final HomeImagelistPayload homePayload = fromJson(json, HomeImagelistPayload.class);
        mDataList.addAll(homePayload.results);
        nowPage++;
    }

    @Override
    public List<HomeImagelistPayload.Images> getDataList () {
        return mDataList;
    }


    @Override
    public List<Integer> getBannerDataList () {
        if (ListUtils.isEmpty(mBannerList)) {
            mBannerList.add(R.mipmap.image1);
            mBannerList.add(R.mipmap.image2);
            mBannerList.add(R.mipmap.image3);
//            mBannerList.add(R.mipmap.image4);
//            mBannerList.add(R.mipmap.image5);
//            mBannerList.add(R.mipmap.image6);
//            mBannerList.add(R.mipmap.image7);
//            mBannerList.add(R.mipmap.image8);
//            mBannerList.add(R.mipmap.image9);
//            mBannerList.add(R.mipmap.image10);
//            mBannerList.add(R.mipmap.image11);
//            mBannerList.add(R.mipmap.image12);
        }
        return mBannerList;
    }

    @Override
    public ArrayList<ImagePassBean> getImagePassBeans () {
        ArrayList<ImagePassBean> list = new ArrayList<>();
        for (HomeImagelistPayload.Images imageBean : mDataList) {
            list.add(new ImagePassBean(imageBean));
        }
        return list;
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
                api = ApiName.HOME_IMAGE + "/12/" + 1;
                resetBaseUrl(ApiName.BASE_1);
                break;
            default:
                api = ApiName.HOME_IMAGE + "/12/" + nowPage;
                break;

        }
        return api;
    }
}