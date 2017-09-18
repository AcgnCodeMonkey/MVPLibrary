package com.xujl.mvpllirary.mvp.view;

import com.xujl.applibrary.mvp.view.DataBindingView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.databinding.ActivityPersonBinding;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingActivityView;

/**
 * Created by xujl on 2017/9/12.
 */
public class PersonDataBindingActivity extends DataBindingView<ActivityPersonBinding> implements IPersonDataBindingActivityView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mDataBinding.activityPersonCommitBtn.setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_person;
    }

    @Override
    public void showResult (String result) {
        mDataBinding.activityPersonResultTV.setText(result);
    }
}