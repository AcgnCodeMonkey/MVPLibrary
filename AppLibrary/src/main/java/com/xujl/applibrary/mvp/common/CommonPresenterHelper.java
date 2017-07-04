package com.xujl.applibrary.mvp.common;

import android.content.Intent;
import android.os.Bundle;

import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;
import com.xujl.baselibrary.utils.ActivityManger;

/**
 * Created by xujl on 2017/7/4.
 */

public class CommonPresenterHelper extends BasePresenterHelper {
    public void exit (BaseActivityPresenter activity) {
        ActivityManger.newInstance().finishActivity(activity);
    }


    public void gotoActivity (BaseActivityPresenter presenter, Class<?> cls, Bundle bundle) {
        final Intent intent = new Intent(presenter, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        presenter.startActivity(intent);
    }


    public void gotoActivity (BaseActivityPresenter presenter, Class<?> cls, Bundle bundle, int requestCode) {
        final Intent intent = new Intent(presenter, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        presenter.startActivityForResult(intent, requestCode);
    }


    public void gotoActivity (BaseActivityPresenter presenter, Class<?> cls) {
        gotoActivity(presenter, cls, null);
    }


    public void gotoActivity (BaseActivityPresenter presenter, Class<?> cls, int requestCode) {
        gotoActivity(presenter, cls, null, requestCode);
    }


    public void backForResult (BaseActivityPresenter presenter, Bundle bundle, int result) {
        if (bundle != null) {
            final Intent intent = new Intent();
            intent.putExtras(bundle);
            presenter.setResult(result, intent);
        } else {
            presenter.setResult(result);
        }
        exit(presenter);
    }

    public void backForResult (BaseActivityPresenter presenter, Intent intent, int result) {
        if (intent != null) {
            presenter.setResult(result, intent);
        } else {
            presenter.setResult(result);
        }
        exit(presenter);
    }

    public void backForResult (BaseActivityPresenter presenter, int result) {
        presenter.setResult(result);
        exit(presenter);
    }


    public void gotoActivity (BaseActivityPresenter presenter, Intent intent, int requestCode) {
        presenter.startActivityForResult(intent, requestCode);
    }

    public void gotoActivity (BaseActivityPresenter presenter, Intent intent) {
        presenter.startActivity(intent);
    }
}
