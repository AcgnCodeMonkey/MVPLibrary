package com.xujl.applibrary.mvp.model;

import com.xujl.applibrary.mvp.common.CommonModelHelper;
import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.baselibrary.mvp.model.BaseModel;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonModel extends BaseModel implements ICommonModel {
    @Override
    public CommonModelHelper getModelHelper () {
        if(!(super.getModelHelper() instanceof  CommonModelHelper)){
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
}
