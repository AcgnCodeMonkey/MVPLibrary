package com.xujl.mvpllirary.mvp.model.port;

import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.mvpllirary.json.PersonPayload;

/**
 * Created by xujl on 2017/9/12.
 */
public interface IPersonDataBindingFragmentModel extends ICommonModel {
    PersonPayload getPersonPayload();
}