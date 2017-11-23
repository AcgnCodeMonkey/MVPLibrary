package com.xujl.myapplication.presenter;

import com.xujl.demo.model.TestActivityModel;
import com.xujl.demo.port.ITestActivityModel;
import com.xujl.demo.port.ITestActivityView;

public class TestActivityPresenter {
    private ITestActivityView mView;
    private ITestActivityModel mModel;

    public TestActivityPresenter (ITestActivityView view) {
        mView = view;
        mModel = new TestActivityModel();
    }


}

