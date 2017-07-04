package com.xujl.utilslibrary.data;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    //遍历集合工具类
    public static <T> void forList (List<T> list, ForListCallBack<T> f) {
        if (list == null)
            return;
        for (int i = 0, length = list.size(); i < length; i++) {
            f.data(i, list.get(i));
        }
    }
    //遍历map工具
    public static <K, V> void forMap (Map<K, V> map, ForMapCallBack<K, V> callBack) {
        if (map == null || map.size() == 0) {
            return;
        }
        Set<K> keySet = map.keySet();
        Iterator<K> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            K key = iterator.next();
            callBack.data(key, map.get(key), iterator);
        }

    }
    public interface ForListCallBack<T> {
        void data (int index, T t);
    }
    public interface ForMapCallBack<K, V> {
        void data (K key, V value, Iterator<K> iterator);
    }

}
