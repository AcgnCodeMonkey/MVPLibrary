package com.xujl.applibrary.mvp.port;

import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.utilslibrary.data.ParamsMapTool;
import com.xujl.utilslibrary.port.RequestCallBack;

/**
 * Created by xujl on 2017/7/4.
 */

public interface ICommonModel extends IBaseModel {
    void requestForGet (int mode,ParamsMapTool paramsMapTool, final RequestCallBack requestCallBack);

}
