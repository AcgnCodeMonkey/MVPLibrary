package com.xujl.datalibrary.nativedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xujl.utilslibrary.view.ViewTool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/5/5.
 * SharedPreferences存储帮助类
 */

public class SharedPreferencesHelper {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPreferencesHelper (Context context, String name, int mode) {
        mPreferences = context.getSharedPreferences(name, mode);
    }

    public SharedPreferencesHelper initEditor () {
        mEditor = mPreferences.edit();
        return this;
    }

    public void commit () {
        mEditor.apply();
        mEditor = null;
    }

    public SharedPreferences getPreferences () {
        return mPreferences;
    }

    public SharedPreferencesHelper putString (String key, String value) {
        mEditor.putString(key, value);
        return this;
    }

    public SharedPreferencesHelper putLong (String key, long value) {
        mEditor.putLong(key, value);
        return this;
    }

    public SharedPreferencesHelper putBoolean (String key, boolean value) {
        mEditor.putBoolean(key, value);
        return this;
    }

    public SharedPreferencesHelper putStringSet (String key, Set<String> value) {
        mEditor.putStringSet(key, value);
        return this;
    }

    public SharedPreferencesHelper putObject (String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(),
                        0));
                return putString(key, string64);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException(
                    "the obj must implement Serializble");
        }
        return this;
    }

    public String getString (String key) {
        return mPreferences.getString(key, "");
    }

    public long getLong (String key) {
        return mPreferences.getLong(key, 0L);
    }

    public boolean getBoolean (String key) {
        return mPreferences.getBoolean(key, false);
    }

    public Set<String> getStringSet (String key) {
        return mPreferences.getStringSet(key, new HashSet<String>());
    }

    public Object getObject (String key) {
        Object obj = null;
        try {
            String base64 = getString(key);
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public <T> SharedPreferencesHelper putList (String key, List<T> list) {
        final Gson gson = new Gson();
        mEditor.putString(key, gson.toJson(list));
        return this;
    }
    public <T> List<T> getList(String key, Class<T> cls){
        List<T> datalist=new ArrayList<>();
        String strJson = mPreferences.getString(key, null);
        if (ViewTool.isEmpty(strJson)) {
            return datalist;
        }
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
        for(final JsonElement elem : array){
            datalist.add(gson.fromJson(elem, cls));
        }
        return datalist;
    }
}
