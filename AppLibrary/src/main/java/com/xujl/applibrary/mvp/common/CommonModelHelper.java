package com.xujl.applibrary.mvp.common;

import com.google.gson.Gson;
import com.xujl.baselibrary.mvp.common.BaseModelHelper;

/**
 * Created by xujl on 2017/7/4.
 */

public class CommonModelHelper extends BaseModelHelper{
    public <T> T fromJson (String json, Class<T> classOfT) {
        final Gson gson = new Gson();
        return gson.fromJson(json,classOfT);
    }

    public String toJson (Object src) {
        final Gson gson = new Gson();
        return gson.toJson(src);
    }
}
