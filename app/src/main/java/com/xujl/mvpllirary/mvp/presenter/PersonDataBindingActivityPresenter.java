package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.applibrary.mvp.presenter.DataBindingActivityPresenter;
import com.xujl.mvpllirary.databinding.ActivityPersonBinding;
import com.xujl.mvpllirary.json.PersonPayload;
import com.xujl.mvpllirary.mvp.model.PersonDataBindingActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IPersonDataBindingActivityModel;
import com.xujl.mvpllirary.mvp.view.PersonDataBindingActivity;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingActivityView;

/**
 * Created by xujl on 2017/9/12.
 */

public class PersonDataBindingActivityPresenter extends DataBindingActivityPresenter
        <IPersonDataBindingActivityView,IPersonDataBindingActivityModel,ActivityPersonBinding>{
    @Override
    protected Class<? extends IPersonDataBindingActivityView> getViewClassType () {
        return PersonDataBindingActivity.class;
    }

    @Override
    protected Class<? extends IPersonDataBindingActivityModel> getModelClassType () {
        return PersonDataBindingActivityModel.class;
    }
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        super.initPresenter(savedInstanceState);
        final PersonPayload payload = new PersonPayload();
        payload.name= "测试勇士";
        payload.phone = "125463355";
        mDataBinding.setPerson(payload);
    }

}
