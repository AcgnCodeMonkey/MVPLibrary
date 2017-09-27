package com.xujl.applibrary.mvp.common;

import android.content.Intent;
import android.os.Bundle;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.applibrary.mvp.port.ICommonPresenter;
import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;
import com.xujl.baselibrary.utils.ActivityManger;
import com.xujl.rxlibrary.BaseObserver;
import com.xujl.utilslibrary.data.ParamsMapTool;
import com.xujl.datalibrary.network.ResultEntity;

import io.reactivex.annotations.NonNull;

/**
 * Created by xujl on 2017/7/4.
 */

public class CommonPresenterHelper extends BasePresenterHelper {
    /**
     * @param mode
     * @param paramsMapTool
     * @param showHint      是否显示加载提示
     */
    public void requestForGet (final int mode, ParamsMapTool paramsMapTool, boolean showHint,
                               ICommonModel model, final ICommonView view, final ICommonPresenter presenter) {
        if (showHint) {
            view.showLoading();
        }
        //网络请求与生命周期绑定，界面被销毁时，不接受回调结果
        model.requestForGet(mode, paramsMapTool, presenter.getRxLife(), new BaseObserver<ResultEntity>() {
            @Override
            public void onNext (@NonNull ResultEntity resultEntity) {
                super.onNext(resultEntity);
                view.dismissLoading();
                if (resultEntity.getErrorCode() == 0) {
                    presenter.requestSuccess(mode, resultEntity.getResultJson());
                    return;
                }
                presenter.requestFailed(mode, resultEntity.getErrorCode(),
                        resultEntity.getErrorString(), resultEntity.getResultJson());
            }
        });
    }

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
