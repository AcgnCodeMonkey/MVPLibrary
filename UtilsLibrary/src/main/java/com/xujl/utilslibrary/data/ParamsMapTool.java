package com.xujl.utilslibrary.data;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 * 参数传递工具类
 */
public class ParamsMapTool {
    private Object mObject;
    private List<String> mList;
    private Intent mIntent;
    private Bundle mBundle;
    private List<?> mObjects;

    public <T>List<T> getObjects () {
        return (List<T>) mObjects;
    }

    public ParamsMapTool putObjects (List<?> objects) {
        mObjects = objects;
        return this;
    }

    public Object getObject () {
        return mObject;
    }

    public ParamsMapTool putObject (Object object) {
        mObject = object;
        return this;
    }

    public ParamsMapTool putIntent (Intent intent) {
        mIntent = intent;
        return this;
    }

    public ParamsMapTool putBundle (Bundle bundle) {
        mBundle = bundle;
        return this;
    }

    public Intent getIntent () {
        return mIntent;
    }

    public Bundle getBundle () {
        return mBundle;
    }

    public ParamsMapTool put (String value) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.add(value);
        return this;
    }

    public String get (int index) {
        if (mList != null) {
            try {
                return StringFormat.checkNull(mList.get(index), StringFormat.NULL);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
