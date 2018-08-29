package com.xujl.applibrary.mvp.port;

import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.datalibrary.network.ResultEntity;
import com.xujl.task.RxLifeList;
import com.xujl.utilslibrary.data.ParamsMapTool;

/**
 * Created by xujl on 2017/7/4.
 */

public interface ICommonModel extends IBaseModel {
    void requestForGet (int mode, ParamsMapTool paramsMapTool, final RxLifeList rxLife, IRequest<ResultEntity> iRequest);

}
