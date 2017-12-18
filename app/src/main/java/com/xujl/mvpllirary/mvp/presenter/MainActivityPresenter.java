package com.xujl.mvpllirary.mvp.presenter;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;

import com.xujl.applibrary.adapter.SimpleViewPagerAdapter;
import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.QRScanHelper;
import com.xujl.mvpllirary.mvp.model.MainActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.mvpllirary.mvp.view.MainActivity;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;
import com.xujl.mvpllirary.util.DemoApplication;
import com.xujl.mvpllirary.util.IntentKey;

/**
 * Created by xujl on 2017/7/4.
 */

public class MainActivityPresenter extends CommonActivityPresenter<IMainActivityView, IMainActivityModel>
        implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected Class<? extends IMainActivityView> getViewClassType () {
        return MainActivity.class;
    }

    @Override
    protected Class<? extends IMainActivityModel> getModelClassType () {
        return MainActivityModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        DemoApplication.getRefWatcher(this).watch(this);
        getPresenterHelper().addHelper(HelperType.TYPE_ONE, new QRScanHelper());
        mView.setAdapter(new SimpleViewPagerAdapter(getSupportFragmentManager(), mModel.getFragmentList()));

    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.part_activity_main_menu_downloadTV:
                final Bundle bundle = new Bundle();
                bundle.putInt(IntentKey.TYPE, ImageBeanType.TYPE_DOWNLOADED);
                gotoActivity(ImageListActivityPresenter.class, bundle);

                break;
            case R.id.part_activity_main_menu_collectionTV:
                final Bundle bundle2 = new Bundle();
                bundle2.putInt(IntentKey.TYPE, ImageBeanType.TYPE_COLLECTION);
                gotoActivity(ImageListActivityPresenter.class, bundle2);
                break;
            case R.id.part_activity_main_menu_personTV:
                gotoActivity(PersonDataBindingActivityPresenter.class);
                break;
            case R.id.toolbar_layout_rightImageBtn:
                final boolean permissionsGet = requestPermissions(new String[]{Manifest.permission.CAMERA});
                if (permissionsGet) {
                    getQRScanHelper().openScanner(exposeActivity());
                }
                break;
            default:

                break;

        }
    }

    @Override
    protected void permissionsComplete (String[] permissions) {
        super.permissionsComplete(permissions);
        getQRScanHelper().openScanner(exposeActivity());
    }

//    @Override
//    protected String[] needPermissions () {
//        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    }

    @Override
    public void onCheckedChanged (RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.activity_main_radioButton1:
                mView.setCurrentItem(0, "妹纸");
                break;
            case R.id.activity_main_radioButton2:
                mView.setCurrentItem(1, "资讯");
                break;
            default:

                break;

        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String result = getQRScanHelper().onActivityResult(requestCode, resultCode, data);
        if (result == null) {
            return;
        }
        mView.showToastMsg(exposeContext(), result, CustomToast.SUCCESS);
    }

    private QRScanHelper getQRScanHelper () {
        return getPresenterHelper().getHelper(HelperType.TYPE_ONE);
    }
}