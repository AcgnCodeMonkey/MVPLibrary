package com.xujl.mvpllirary;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2018/5/15.
 */

public class JsonBean {
    public String success;
    public Data data;

    public static class Data {
        public List<ListBean> same_res;
        public List<ListBean> similar_res;
    }

    public static class ListBean {
        public String t_id;
        public String t_name;
        public String t;
        public String mo_count;

    }

    public static void test () {
        String json = "{\"success\":true,\"data\":{\"same_res\":[],\"similar_res\":[{\"t_id\":\"59\",\"t_name\":\"饲猫达人\",\"t\":\"9\",\"mo_count\":\"10\"}]}}";
        Gson gson = new Gson();
        final JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
        final List<ListBean> beanList = count(jsonBean.data.similar_res, jsonBean.data.same_res);
    }

    private static List<ListBean> count (List<ListBean> list1, List<ListBean> list2) {
        List<ListBean> result = new ArrayList<>();
        if (list1 != null) {
            result.addAll(list1);
        }
        if (list2 != null) {
            result.addAll(list2);
        }
        return result;
    }
}
