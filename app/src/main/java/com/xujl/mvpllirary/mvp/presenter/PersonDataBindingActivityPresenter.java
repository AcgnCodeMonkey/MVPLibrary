package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.model.PersonDataBindingActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IPersonDataBindingActivityModel;
import com.xujl.mvpllirary.mvp.view.PersonDataBindingActivity;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingActivityView;

/**
 * Created by xujl on 2017/9/12.
 */

public class PersonDataBindingActivityPresenter extends CommonActivityPresenter<IPersonDataBindingActivityView,
        IPersonDataBindingActivityModel> {
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
        mView.setPerson(mModel.getPersonPayload());//建立数据与视图的绑定关系
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
      switch(v.getId()){
          case  R.id.activity_person_commitBtn:
          mView.showResult(mModel.getPersonPayload().toString());
          break;
          default:

          break;

      }
    }
}
