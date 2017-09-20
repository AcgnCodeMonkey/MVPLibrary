package com.xujl.mvpllirary.mvp.model;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.mvpllirary.json.PersonPayload;
import com.xujl.mvpllirary.mvp.model.port.IPersonDataBindingActivityModel;

/**
 * Created by xujl on 2017/9/12.
 */
public class PersonDataBindingActivityModel extends CommonModel implements IPersonDataBindingActivityModel {
    private PersonPayload mPersonPayload;

    public PersonDataBindingActivityModel () {
        mPersonPayload = new PersonPayload();
        mPersonPayload.setName("测试勇士");
        mPersonPayload.setPhone("45132156");
    }

    @Override
    public PersonPayload getPersonPayload () {
        return mPersonPayload;
    }
}