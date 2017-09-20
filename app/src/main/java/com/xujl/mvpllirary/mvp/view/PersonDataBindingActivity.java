package com.xujl.mvpllirary.mvp.view;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.databinding.ActivityPersonBinding;
import com.xujl.mvpllirary.json.PersonPayload;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingActivityView;

/**
 * Created by xujl on 2017/9/12.
 */
public class PersonDataBindingActivity extends CommonView implements IPersonDataBindingActivityView {
    private ActivityPersonBinding mBinding;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mBinding = (ActivityPersonBinding) getDataBinding();
        mBinding.activityPersonCommitBtn.setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_person;
    }

    @Override
    public boolean enableDataBinding () {
        return true;
    }


    @Override
    public void showResult (String result) {
        mBinding.activityPersonResultTV.setText(result);
    }

    @Override
    public void setPerson (PersonPayload personPayload) {
        mBinding.setPerson(personPayload);
    }
}