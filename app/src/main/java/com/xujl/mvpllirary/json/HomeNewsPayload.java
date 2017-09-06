package com.xujl.mvpllirary.json;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */

public class HomeNewsPayload {
    public String error;
    public List<News> results;

    public static class News {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String used;
        public String url;
        public String who;
        public List<String> images;//图片
    }
}
