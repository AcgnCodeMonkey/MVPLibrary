package com.xujl.baselibrary.mvp.model;

import com.xujl.baselibrary.mvp.common.BaseModelHelper;
import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

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
 * Created by xujl on 2017/4/28.
 */

public abstract class BaseModel implements IBaseModel {
    protected BaseModelHelper mModelHelper;

    @Override
    public void initModel (IBasePresenter presenter) {

    }

    public BaseModelHelper getModelHelper () {
        if (mModelHelper == null) {
            mModelHelper = new BaseModelHelper();
        }
        return mModelHelper;
    }

    public void setModelHelper (BaseModelHelper modelHelper) {
        mModelHelper = modelHelper;
    }
}
