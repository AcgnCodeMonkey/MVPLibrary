package com.xujl.mvpllirary.mvp.view;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.databinding.FragmentPersonBinding;
import com.xujl.mvpllirary.json.PersonPayload;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingFragmentView;

/**
 * Created by xujl on 2017/9/12.
 */
public class PersonDataBindingFragment extends CommonView implements IPersonDataBindingFragmentView {
    private FragmentPersonBinding mBinding;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mBinding =  getDataBinding();
        mBinding.activityPersonCommitBtn.setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_person;
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