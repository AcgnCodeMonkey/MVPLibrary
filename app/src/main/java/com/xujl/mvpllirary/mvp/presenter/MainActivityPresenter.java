package com.xujl.mvpllirary.mvp.presenter;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;

import com.xujl.applibrary.adapter.SimpleViewPagerAdapter;
import com.xujl.applibrary.db.ImageBeanType;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.model.MainActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.mvpllirary.mvp.view.MainActivity;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;
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
            default:

                break;

        }
    }


    @Override
    protected String[] needPermissions () {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

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
}