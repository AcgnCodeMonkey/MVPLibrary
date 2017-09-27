package com.xujl.applibrary.mvp.port;

import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.rxlibrary.BaseObserver;
import com.xujl.rxlibrary.RxLife;
import com.xujl.utilslibrary.data.ParamsMapTool;
import com.xujl.datalibrary.network.ResultEntity;

/**
 * Created by xujl on 2017/7/4.
 */

public interface ICommonModel extends IBaseModel {
    void requestForGet (int mode, ParamsMapTool paramsMapTool, final RxLife rxLife, BaseObserver<ResultEntity> observer);

}
