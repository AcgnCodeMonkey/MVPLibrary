package com.xujl.mvpllirary.mvp.presenter;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.QRScanHelper;
import com.xujl.mvpllirary.mvp.model.MainFragmentModel;
import com.xujl.mvpllirary.mvp.model.port.IMainFragmentModel;
import com.xujl.mvpllirary.mvp.view.MainFragment;
import com.xujl.mvpllirary.mvp.view.port.IMainFragmentView;
import com.xujl.mvpllirary.util.IntentKey;

/**
 * Created by xujl on 2017/7/4.
 */

public class MainFragmentPresenter extends CommonFragmentPresenter<IMainFragmentView, IMainFragmentModel>
        implements CompoundButton.OnCheckedChangeListener {
    public static MainFragmentPresenter newInstance () {

        Bundle args = new Bundle();

        MainFragmentPresenter fragment = new MainFragmentPresenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<? extends IMainFragmentView> getViewClassType () {
        return MainFragment.class;
    }

    @Override
    protected Class<? extends IMainFragmentModel> getModelClassType () {
        return MainFragmentModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        final HomeImageListFragmentPresenter homeImageListFragmentPresenter = findChildFragment(HomeImageListFragmentPresenter.class);
        if (homeImageListFragmentPresenter == null) {
            mModel.setFragments(MainFragmentPresenter.this, true);
            mView.loadMultipleRootFragment(MainFragmentPresenter.this, mModel.getFragments());
        } else {
            mModel.setFragments(MainFragmentPresenter.this, false);
        }
        getPresenterHelper().addHelper(new QRScanHelper());

    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.part_activity_main_menu_downloadTV:
                final Bundle bundle = new Bundle();
                bundle.putInt(IntentKey.TYPE, ImageBeanType.TYPE_DOWNLOADED);
                start(ImageListFragmentPresenter.newInstance(bundle));
                break;
            case R.id.part_activity_main_menu_collectionTV:
                final Bundle bundle2 = new Bundle();
                bundle2.putInt(IntentKey.TYPE, ImageBeanType.TYPE_COLLECTION);
                start(ImageListFragmentPresenter.newInstance(bundle2));
                break;
            case R.id.part_activity_main_menu_personTV:
                start(PersonDataBindingFragmentPresenter.newInstance());
                break;
            case R.id.toolbar_layout_rightImageBtn:
                final boolean permissionsGet = requestPermissions(new String[]{Manifest.permission.CAMERA});
                if (permissionsGet) {
                    getQRScanHelper().openScanner(this);
                }
                break;
            default:

                break;

        }
    }

    @Override
    protected void permissionsComplete (String[] permissions) {
        super.permissionsComplete(permissions);
        getQRScanHelper().openScanner(this);
    }

//    @Override
//    protected String[] needPermissions () {
//        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    }



    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String result = getQRScanHelper().onActivityResult(requestCode, resultCode, data);
        if (result == null) {
            return;
        }
        mView.showToastMsg(exposeContext(), result, CustomToast.SUCCESS);
    }

    private QRScanHelper getQRScanHelper () {
        return getPresenterHelper().getHelper(QRScanHelper.class);
    }

    @Override
    public boolean enableToolBar () {
        return true;
    }

    @Override
    public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mView.setCurrentItem(MainFragmentPresenter.this, mModel.getFragments(), 0, "妹纸");
        } else {
            mView.setCurrentItem(MainFragmentPresenter.this, mModel.getFragments(), 1, "资讯");
        }
    }
}