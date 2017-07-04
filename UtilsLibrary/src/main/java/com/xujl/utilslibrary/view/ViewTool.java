package com.xujl.utilslibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xujl.utilslibrary.system.DensityUtil;
import com.xujl.utilslibrary.system.Log;

/**
 * Created by Administrator on 2016/6/18.
 * view工具类
 */
public class ViewTool {
    //关闭键盘
    public static void closeKeyboard (View inputText) {
        if (inputText == null) {
            Log.e("ViewTool->closeKeyboard", "空引用");
            return;
        }
        InputMethodManager imm = (InputMethodManager) inputText.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0); // 强制隐藏键盘
    }


    //强制显示软键盘
    public static void showKeyboard (TextView inputText) {
        if (inputText == null) {
            Log.e("ViewTool->showKeyboard", "空引用");
            return;
        }
        InputMethodManager imm = (InputMethodManager) inputText.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    //获取文本控件上的内容
    public static String viewGetText (TextView textView) {
        if (textView == null) {
            Log.e("ViewTool->viewGetText", "空引用");
            return "";
        }
        String s = textView.getText().toString();
        if (ViewTool.isEmpty(s)) {
            return "";
        }
        return s.trim();
    }

    /**
     * 改变图标大小
     * direction,0，左，1,上。2，右，3，下
     */
    public static void changeDrawable (RadioButton radioBtn, int direction, int height, int width) {
        if (radioBtn == null) {
            Log.e("ViewTool->changeDrawable", "空引用");
            return;
        }
        Drawable[] drawables = radioBtn.getCompoundDrawables();
        drawables[direction].setBounds(0, 0, (int) DensityUtil.dip2px(width),
                (int) DensityUtil.dip2px(height));
        radioBtn.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    /**
     * 改变图标大小
     * direction,0，左，1,上。2，右，3，下
     */
    public static void changeDrawable (Button btn, int direction, int height, int width) {
        if (btn == null) {
            Log.e("ViewTool->changeDrawable", "空引用");
            return;
        }
        Drawable[] drawables = btn.getCompoundDrawables();
        drawables[direction].setBounds(0, 0, (int) DensityUtil.dip2px(width),
                (int) DensityUtil.dip2px(height));
        btn.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    /**
     * 改变图标大小
     * direction,0，左，1,上。2，右，3，下
     */
    public static void changeDrawable (TextView textView, int direction, int height, int width) {
        if (textView == null) {
            Log.e("ViewTool->changeDrawable", "空引用");
            return;
        }
        Drawable[] drawables = textView.getCompoundDrawables();
        drawables[direction].setBounds(0, 0, (int) DensityUtil.dip2px(width),
                (int) DensityUtil.dip2px(height));
        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    /**
     * 改变图标大小
     * direction,0，左，1,上。2，右，3，下
     */
    public static void changeDrawable (CheckBox checkBox, int direction, int height, int width) {
        if (checkBox == null) {
            Log.e("ViewTool->changeDrawable", "空引用");
            return;
        }
        Drawable[] drawables = checkBox.getCompoundDrawables();
        drawables[direction].setBounds(0, 0, (int) DensityUtil.dip2px(width),
                (int) DensityUtil.dip2px(height));
        checkBox.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    //限制键盘只能弹出数字键盘
    public static void inputTypeNumber (TextView editText) {
        if (editText == null) {
            Log.e("ViewTool->inputTypeNumber", "空引用");
            return;
        }
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    //判断是否字符串为空
    public static boolean isEmpty (String str) {
        return str == null || str.equals("") || str.equals("null") || str.equals("NULL");
    }

    /**
     * 判断控件或文本控件上的文字是否为空
     *
     * @param textView 传入需要判断的文本控件
     * @return 返回内容是否为空，控件为Null时也返回false
     */
    public static boolean isEmpty (TextView textView) {
        return textView == null || isEmpty(viewGetText(textView));
    }

    //让listView可以自适应高度，需要在设置适配器后调用
    public static void setListViewHeightBasedOnChildren (ListView listView) {
        if (listView == null) {
            Log.e("ViewTool->setListViewHeightBasedOnChildren", "空引用");
            return;
        }
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        listView.setLayoutParams(params);
    }

    /**
     * 设置listview显示指定行数（行数没超过指定行数则自适应高度）
     *
     * @param listView
     * @param showCount 需要显示的最大行数
     */
    public static void setListViewHeightForCount (ListView listView, int showCount) {
        if (listView == null) {
            Log.e("ViewTool->setListViewHeightBasedOnChildren", "空引用");
            return;
        }
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int maxCount = listAdapter.getCount();//最大行数
        if (maxCount > showCount) {
            maxCount = showCount;
        }
        for (int i = 0; i < maxCount; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        listView.setLayoutParams(params);
    }


}
