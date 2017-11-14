package com.xujl.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

public class MainActivity extends CommonView implements IMainActivityView {
    private FrameLayout mFrameLayout;

    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mFrameLayout = findView(R.id.fm);
        findView(R.id.fg1).setOnClickListener(presenter);
        findView(R.id.fg2).setOnClickListener(presenter);
        findView(R.id.sender1).setOnClickListener(presenter);
        findView(R.id.sender2).setOnClickListener(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_main;
    }

    @Override
    public void initFragment (FragmentManager supportFragmentManager, Fragment... fragments) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.fm, fragments[0]);
        transaction.add(R.id.fm, fragments[1]);
        transaction.show(fragments[0]);
        transaction.hide(fragments[1]);
        transaction.commit();
    }

    @Override
    public void showFragment (FragmentManager supportFragmentManager,Fragment showFragment,Fragment... fragments) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            if(fragment == showFragment){
                transaction.show(fragment);
            }else{
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }
}
