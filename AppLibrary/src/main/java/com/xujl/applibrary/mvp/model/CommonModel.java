package com.xujl.applibrary.mvp.model;

import android.support.annotation.Nullable;

import com.xujl.applibrary.mvp.common.CommonModelHelper;
import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.applibrary.mvp.port.IRequest;
import com.xujl.baselibrary.mvp.model.BaseModel;
import com.xujl.datalibrary.network.InternetUtil;
import com.xujl.datalibrary.network.ResultEntity;
import com.xujl.task.Emitter;
import com.xujl.task.RxExecutor;
import com.xujl.task.RxLifeList;
import com.xujl.task.Task;
import com.xujl.utilslibrary.data.ParamsMapTool;
import com.xujl.utilslibrary.port.RequestCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonModel extends BaseModel implements ICommonModel {
    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 3;
    public static final int MODE_4 = 4;
    public static final int MODE_5 = 5;
    protected InternetUtil mInternetUtil;

    @Override
    public CommonModelHelper getModelHelper () {
        if (!(super.getModelHelper() instanceof CommonModelHelper)) {
            setModelHelper(new CommonModelHelper());
        }
        return (CommonModelHelper) super.getModelHelper();
    }


    @Override
    public <T> T fromJson (String json, Class<T> classOfT) {
        return getModelHelper().fromJson(json, classOfT);
    }

    @Override
    public String toJson (Object src) {
        return getModelHelper().toJson(src);
    }

    @Override
    public void requestForGet (int mode, ParamsMapTool paramsMapTool, RxLifeList rxLife, final IRequest<ResultEntity> iRequest) {
        final String apiName = getApiName(mode);
        final Map<String, Object> params = new HashMap<>();
        addParams(mode, params, paramsMapTool);
        final RequestCallBack requestCallBack = new RequestCallBack() {
            @Override
            public void notice (String json) {
                iRequest.onNext(new ResultEntity(json));
            }

            @Override
            public void error (@JsonICode int error, @Nullable String json) {
                iRequest.onNext(new ResultEntity(json, error));
            }
        };
        RxExecutor.getInstance()
                .executeTask(new Task<Object>() {
                    @Override
                    public void run (final Emitter e) throws Exception {
                        super.run(e);
                        mInternetUtil.requestForGet(params, "", requestCallBack, apiName);
                    }

                    @Override
                    public void onNext (Object object) {
                        super.onNext(object);
                        iRequest.onNext((ResultEntity) object);
                    }
                });
    }

    protected void addParams (int mode, Map<String, Object> params, ParamsMapTool paramsMapTool) {

    }

    protected void resetBaseUrl (String baseUrl) {
        mInternetUtil = new InternetUtil(baseUrl);
    }

    protected String getApiName (int mode) {
        return null;
    }
}
