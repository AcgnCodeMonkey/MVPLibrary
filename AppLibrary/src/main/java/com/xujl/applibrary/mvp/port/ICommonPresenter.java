package com.xujl.applibrary.mvp.port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public interface ICommonPresenter extends IBasePresenter {
    <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle);

    <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle, int requestCode);

    <S extends Activity> void gotoActivity (Class<S> cls);

    void gotoActivity (Intent intent);

    void gotoActivity (Intent intent, int requestCode);

    <S extends Activity> void gotoActivity (Class<S> cls, int requestCode);

    void backForResult (Bundle bundle, int result);//返回activity，需要传入返回码

    void backForResult (Intent intent, int result);//返回activity，需要传入返回码

    void backForResult (int result);//返回activity，需要传入返回码
}
