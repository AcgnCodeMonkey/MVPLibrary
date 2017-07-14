package com.xujl.mvpllirary.mvp.model;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.datalibrary.network.ApiName;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomePayload;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.utilslibrary.data.ListUtils;
import com.xujl.utilslibrary.data.ParamsMapTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivityModel extends CommonModel implements IMainActivityModel {
    private List<HomePayload.Images> mDataList;
    private final List<String> titles = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
    private final List<Integer> mBannerList = new ArrayList<>();
    public MainActivityModel () {
        mDataList = new ArrayList<>();
    }

    @Override
    public void addData (int mode, String json) {
        if (mode == MODE_1) {
            mDataList.clear();
        }
        final HomePayload homePayload = fromJson(json, HomePayload.class);
        homePayload.imgs.remove(homePayload.imgs.size()-1);
        mDataList.addAll( homePayload.imgs);
    }

    @Override
    public List<HomePayload.Images> getDataList () {
        return mDataList;
    }

    @Override
    public List<String> getTitles () {
        return titles;
    }

    @Override
    public List<Integer> getBannerDataList () {
        if(ListUtils.isEmpty(mBannerList)){
            mBannerList.add(R.mipmap.image1);
            mBannerList.add(R.mipmap.image2);
            mBannerList.add(R.mipmap.image3);
            mBannerList.add(R.mipmap.image4);
            mBannerList.add(R.mipmap.image5);
            mBannerList.add(R.mipmap.image6);
            mBannerList.add(R.mipmap.image7);
            mBannerList.add(R.mipmap.image8);
            mBannerList.add(R.mipmap.image9);
            mBannerList.add(R.mipmap.image10);
            mBannerList.add(R.mipmap.image11);
            mBannerList.add(R.mipmap.image12);
        }
        return mBannerList;
    }

    @Override
    protected void addParams (int mode, Map<String, Object> params, ParamsMapTool paramsMapTool) {
        switch (mode) {
            default:
                //col=大类&tag=分类&sort=0&pn=开始条数&rn=显示数量&p=channel&from=1
                params.put("col", "美女");
                params.put("tag", "小清新");
                params.put("sort", "0");
                params.put("pn", "" + mDataList.size());
                params.put("rn", "12");
                params.put("p", "channel");
                params.put("from", "1");
                break;

        }
    }

    @Override
    protected String getApiName (int mode) {
        String api = null;
        switch (mode) {
            default:
                api = ApiName.HOME_IMAGE;
                break;

        }
        return api;
    }
}