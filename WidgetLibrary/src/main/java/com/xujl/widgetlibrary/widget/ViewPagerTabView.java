package com.xujl.widgetlibrary.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;

/**
 * Created by xujl on 2017/9/6.
 */

public class ViewPagerTabView extends ScrollIndicatorView {
    public ViewPagerTabView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private IndicatorViewPager mIndicatorViewPager;
    public  void setIndicatorViewPager(ViewPager viewPager){
        mIndicatorViewPager = new IndicatorViewPager(this,viewPager);
    }
    public void setAdapter (BaseAdapter adapter) {
        mIndicatorViewPager.setAdapter(adapter);
    }

    @Override
    public void setCurrentItem (int item, boolean anim) {
        super.setCurrentItem(item, anim);

    }
    public static abstract class BaseAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
        public BaseAdapter (FragmentManager fragmentManager) {
            super(fragmentManager);
        }
    }
}
