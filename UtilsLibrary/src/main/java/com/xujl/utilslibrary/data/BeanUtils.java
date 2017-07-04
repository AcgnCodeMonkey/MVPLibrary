package com.xujl.utilslibrary.data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 * 实体类拷贝工具
 */
public class BeanUtils {
    //复制字段名相同的属性给其他对象,公有字段无get,set方法
    public static <T, S> void copyBean (T target, S source) {
        // 获取对象类型
        Class<?> targetClass = target.getClass();
        Class<?> sourceClass = source.getClass();

        // 获得源对象所有属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        // 获得目标对象所有属性
        Field[] targetFields = targetClass.getDeclaredFields();
        //遍历源对象所有字段

        for (int j = 0; j < targetFields.length; j++) {
            Field targetField = targetFields[j];
            boolean flag = true;//字段值是否被未赋值
            targetField.setAccessible(true);
            if (targetField.getType() != String.class) {//过滤掉非String字段
                continue;
            }
            for (int i = 0; i < sourceFields.length; i++) {
                Field sourceField = sourceFields[i];
                sourceField.setAccessible(true);//设置字段可以访问
                if (sourceField.getType() != String.class) {//过滤掉非String字段
                    continue;
                }
                if (sourceField.getName().equals(targetField.getName())) {//获取相同名字的字段
                    try {
                        String sourceFieldValue = (String) sourceField.get(source);//取值
                        targetField.set(target, sourceFieldValue);//拷贝
                        flag = false;
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
            if (flag) {//当字段未赋值时，默认赋值为""
                try {
                    targetField.set(target, "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //复制字段名相同的属性给其他对象,私有字段
    public static <T, S> void copyPrivateBean (T target, S source) {
        // 获取对象类型
        Class<?> targetClass = target.getClass();
        Class<?> sourceClass = source.getClass();

        // 获得源对象所有属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        // 获得目标对象所有属性
        Field[] targetFields = targetClass.getDeclaredFields();
        //遍历源对象所有字段

        for (int j = 0; j < targetFields.length; j++) {
            Field targetField = targetFields[j];
            boolean flag = true;//字段值是否被未赋值
            targetField.setAccessible(true);
            if (targetField.getType() != String.class) {//过滤掉非String字段
                continue;
            }
            for (int i = 0; i < sourceFields.length; i++) {
                Field sourceField = sourceFields[i];
                sourceField.setAccessible(true);//设置字段可以访问
                if (sourceField.getType() != String.class) {//过滤掉非String字段
                    continue;
                }
                if (sourceField.getName().equals(targetField.getName())) {//获取相同名字的字段
                    try {
                        String sourceFieldValue = (String) sourceField.get(source);//取值
                        targetField.set(target, sourceFieldValue);//拷贝
                        flag = false;
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
            if (flag) {//当字段未赋值时，默认赋值为""
                try {
                    targetField.set(target, "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static <T,S> List<T> getList(S obj){
        Class<?> sourceClass = obj.getClass();
        // 获得目标对象所有属性
        Field[] targetFields = sourceClass.getDeclaredFields();
        for (int j = 0; j < targetFields.length; j++) {
            Field targetField = targetFields[j];
            targetField.setAccessible(true);
            List<T> list = null;
            if (targetField.getType() == List.class) {//过滤掉非String字段
                try {
                    list  = (List<T>) targetField.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return  list;
            }
        }
        return null;
    }
}
