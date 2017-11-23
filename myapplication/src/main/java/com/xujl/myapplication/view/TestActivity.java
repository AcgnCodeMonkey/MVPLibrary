package com.xujl.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xujl.demo.port.ITestActivityView;
import com.xujl.demo.presenter.TestActivityPresenter;
import com.xujl.myapplication.R;

class TestActivityView extends AppCompatActivity implements ITestActivityView {
    private TestActivityPresenter mPresenter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mPresenter = new TestActivityPresenter(this);
    }

}

