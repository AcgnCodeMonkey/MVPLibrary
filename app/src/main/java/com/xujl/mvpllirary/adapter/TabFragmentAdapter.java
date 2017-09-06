package com.xujl.mvpllirary.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.xujl.mvpllirary.R;
import com.xujl.utilslibrary.system.DensityUtil;
import com.xujl.widgetlibrary.widget.ViewPagerTabView;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */

public class TabFragmentAdapter extends ViewPagerTabView.BaseAdapter {
    private List<String> mTitles;
    private List<Fragment> mFragmentList;
    private Context mContext;

    public TabFragmentAdapter (FragmentManager fragmentManager, Context context, List<String> titles, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mContext = context;
        mTitles = titles;
        mFragmentList = fragmentList;
    }

    @Override
    public int getCount () {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public View getViewForTab (int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tab_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(mTitles.get(position));
        int witdh = getTextWidth(textView);
        int padding = (int) DensityUtil.dip2px(8);
        //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
        //1.3f是根据上面字体大小变化的倍数1.3f设置
        textView.setWidth((int) (witdh * 1.3f) + padding);
        return convertView;
    }

    private int getTextWidth (TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

    @Override
    public Fragment getFragmentForPage (int position) {
        return mFragmentList.get(position);
    }

    /**
     *
     * @param tabView
     * @param context
     * @param maxTitle 长度最长的标题
     */
    public static void initTitleView (ViewPagerTabView tabView, Context context, String maxTitle) {
        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        tabView.setOnTransitionListener(new OnTransitionTextListener().setColor(context.getResources().getColor(R.color.colorAccent),
                context.getResources().getColor(R.color.textHintColor)).setSize(selectSize, unSelectSize));
        final ColorBar colorBar = new ColorBar(context, context.getResources().getColor(R.color.colorAccent), 4);
        Paint paint = new Paint();
        paint.setTextSize(DensityUtil.sp2px(18));
        final float measureText = paint.measureText(maxTitle);
        colorBar.setWidth((int) measureText);
        tabView.setScrollBar(colorBar);
    }


}
