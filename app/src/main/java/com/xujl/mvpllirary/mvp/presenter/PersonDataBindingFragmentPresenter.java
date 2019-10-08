package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.model.port.IPersonDataBindingFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IPersonDataBindingFragmentView;

import androidx.annotation.Nullable;

/**
 * Created by xujl on 2017/9/12.
 */

public class PersonDataBindingFragmentPresenter extends CommonFragmentPresenter<IPersonDataBindingFragmentView,
        IPersonDataBindingFragmentModel> {
    public static PersonDataBindingFragmentPresenter newInstance () {

        Bundle args = new Bundle();
        PersonDataBindingFragmentPresenter fragment = new PersonDataBindingFragmentPresenter();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mView.setPerson(mModel.getPersonPayload());//建立数据与视图的绑定关系
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return attachToSwipeBack(super.onCreateView(inflater, container, savedInstanceState));
    }

    @Override
    public boolean enableToolBar () {
        return true;
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.activity_person_commitBtn:
                mView.showResult(mModel.getPersonPayload().toString());
                break;
            default:

                break;

        }
    }
}
