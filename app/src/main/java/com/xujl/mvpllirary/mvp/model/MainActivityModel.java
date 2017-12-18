package com.xujl.mvpllirary.mvp.model;

import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.mvpllirary.mvp.presenter.HomeImageListFragmentPresenter;
import com.xujl.mvpllirary.mvp.presenter.HomeNewsFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivityModel extends CommonModel implements IMainActivityModel {

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
    public List<Fragment> getFragmentList () {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeImageListFragmentPresenter());
        fragmentList.add(new HomeNewsFragmentPresenter());
        return fragmentList;
    }
}