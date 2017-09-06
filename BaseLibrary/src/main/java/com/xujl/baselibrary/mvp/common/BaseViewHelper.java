package com.xujl.baselibrary.mvp.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/5/2.
 */

public class BaseViewHelper extends BaseMvpHelper {
    private View mParentView;

    /**
     *
     * @param layoutId
     * @param context
     * @param isAddParentLayout 是否为内容布局自动嵌套父布局
     * @return
     */
    public View inflateLayout (int layoutId, Context context,boolean isAddParentLayout) {
        if(isAddParentLayout){
            mParentView = new LinearLayout(context);
            mParentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((LinearLayout)mParentView).setOrientation(LinearLayout.VERTICAL);
            View contentView = LayoutInflater.from(context).inflate(layoutId, null);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((LinearLayout)mParentView).addView(contentView);
        }else{
            mParentView = LayoutInflater.from(context).inflate(layoutId, null);
            mParentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return mParentView;
    }

    public View getParentLayout () {
        return mParentView;
    }
}
