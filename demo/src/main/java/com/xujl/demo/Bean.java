package com.xujl.demo;

import java.util.List;

/**
 * Created by xujl on 2017/7/11.
 */

public class Bean {
    public String title_1;//一级标题
    public List<Bean2> bean2List;

    public Bean (String title_1, List<Bean2> bean2List) {
        this.title_1 = title_1;
        this.bean2List = bean2List;
    }

    public static class Bean2{
        public String title_2;//二级标题
        public int maxChexked;//最多允许选中几个

        public Bean2 (String title_2, int maxChexked, List<ItemBean> itemBeanList) {
            this.title_2 = title_2;
            this.maxChexked = maxChexked;
            this.itemBeanList = itemBeanList;
        }

        public List<ItemBean> itemBeanList;
    }
    public static class ItemBean{
        public boolean isChecked;//是否选中
        public int id;//选项id
        public String content;//选项内容

        public ItemBean (boolean isChecked, int id, String content) {
            this.isChecked = isChecked;
            this.id = id;
            this.content = content;
        }
    }
}
