package com.xujl.baselibrary.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by xujl on 2017/3/13.
 * 数组集合相关工具类
 */

public class ListUtils {
    public static boolean isEmpty (Map list) {
        return list == null || list.size() == 0;
    }
    public static boolean isEmpty (List list) {
        return list == null || list.size() == 0;
    }
    public static  int getSize (List list) {
        return list == null ? 0 : list.size();
    }
    public static boolean isEmpty (int[] list) {
        return list == null || list.length == 0;
    }
    public static <T> boolean isEmpty (T[] list) {
        return list == null || list.length == 0;
    }
    public static <T> int getSize (T[] list) {
        return list == null ? 0 : list.length;
    }

}
