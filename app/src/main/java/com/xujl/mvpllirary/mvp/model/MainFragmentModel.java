package com.xujl.mvpllirary.mvp.model;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.mvp.model.port.IMainFragmentModel;
import com.xujl.mvpllirary.mvp.presenter.HomeImageListFragmentPresenter;
import com.xujl.mvpllirary.mvp.presenter.HomeNewsFragmentPresenter;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainFragmentModel extends CommonModel implements IMainFragmentModel {
    private ISupportFragment[] mFragments = new ISupportFragment[2];

    @Override
    public void initModel (IBasePresenter presenter) {
        super.initModel(presenter);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ISupportFragment[] getFragments () {
        return mFragments;
    }

    @Override
    public void setFragments (SupportFragment supportFragment, boolean isFirstLoad) {
        if (isFirstLoad) {
            mFragments = new ISupportFragment[]{HomeImageListFragmentPresenter.newInstance(), HomeNewsFragmentPresenter.newInstance()};
            return;
        }
        mFragments[0] = supportFragment.findChildFragment(HomeImageListFragmentPresenter.class);
        mFragments[1] = supportFragment.findChildFragment(HomeNewsFragmentPresenter.class);
    }
}