package com.xujl.utilslibrary.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络连接工具类
 */
public abstract class NetworkUtil {
    private List<String> mTagList;//请求tag集合

    public NetworkUtil () {
        mTagList = new ArrayList<>();
    }

    public static Gson getGson () {
        Gson gson;
        GsonBuilder gb = new GsonBuilder();//过滤null
        gb.registerTypeAdapterFactory(NullTypeAdapter.FACTORY);
        gson = gb.create();
        return gson;
    }

    /**
     * 取消所有请求
     */
    public abstract void cancelAllRequest ();

    /**
     * 取消指定tag的请求
     */
    public abstract void cancelRequestForTag (String tag);

    /**
     * 添加一个请求tag
     * @param tag
     */
    public void addRequestTag (String tag) {
        if (!mTagList.contains(tag)) {
            mTagList.add(tag);
        }
    }

}
