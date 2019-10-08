package com.xujl.mvpllirary.mvp.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.view.port.IMainFragmentView;

import java.lang.reflect.Field;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.customview.widget.ViewDragHelper;
import androidx.drawerlayout.widget.DrawerLayout;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainFragment extends CommonView implements IMainFragmentView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void initView (final IBasePresenter presenter) {
        super.initView(presenter);
        mDrawerLayout = findView(R.id.activity_main_drawerLayout);
        getToolBarModule().setTitle("妹纸");
//        getToolBarModule().getActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getToolBarModule().getActionBar().setDisplayHomeAsUpEnabled(true);
        getToolBarModule().getRightIB().setImageResource(R.drawable.qr_code_icon);
        getToolBarModule().getRightIB().setOnClickListener(presenter);
        mDrawerToggle = new ActionBarDrawerToggle(presenter.exposeActivity(), mDrawerLayout, getToolBarModule().getToolbar(), R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed (View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setDrawerLeftEdgeSize(presenter.exposeActivity(),mDrawerLayout,0f);
        mRootView.findViewById(R.id.part_activity_main_menu_downloadTV).setOnClickListener(presenter);
        mRootView.findViewById(R.id.part_activity_main_menu_collectionTV).setOnClickListener(presenter);
        mRootView.findViewById(R.id.part_activity_main_menu_personTV).setOnClickListener(presenter);
        ((CheckBox) mRootView.findViewById(R.id.cb_menu)).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) presenter);
    }

    public static void setDrawerLeftEdgeSize (Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_main;
    }


    @Override
    public void setCurrentItem (SupportFragment supportFragment, ISupportFragment[] fragments, int position, String title) {
        getToolBarModule().setTitle(title);
        switch (position) {
            case 0:
                supportFragment.showHideFragment(fragments[0], fragments[1]);
                break;
            case 1:
                supportFragment.showHideFragment(fragments[1], fragments[0]);
                break;
            default:
                break;

        }
    }

    @Override
    public void loadMultipleRootFragment (SupportFragment supportFragment, ISupportFragment[] fragments) {
        supportFragment.loadMultipleRootFragment(R.id.root_fl, 0, fragments);
    }


}