package com.xujl.applibrary.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.Toast;

import com.xujl.applibrary.R;

import es.dmoral.toasty.Toasty;


/**
 * Created by Administrator on 2016/6/3.
 */
public class CustomToast {
    public static final int LENGTH_SHORT = 500;
    public static final int LENGTH_LONG = 1000;
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int LOADING = 4;

    public static void showToast (Context context, String msg, int code) {
        final Resources resources = context.getResources();
        switch (code) {
            case SUCCESS:
                Toasty.custom(context, msg, resources.getDrawable(es.dmoral.toasty.R.drawable.ic_check_white_48dp), Color.WHITE,
                        resources.getColor(R.color.successColor), Toast.LENGTH_SHORT, true, true).show();
                break;
            case ERROR:
                Toasty.custom(context, msg, resources.getDrawable(es.dmoral.toasty.R.drawable.ic_clear_white_48dp), Color.WHITE,
                        resources.getColor(R.color.errorColor), Toast.LENGTH_SHORT, true, true).show();
                break;
            case INFO:
                Toasty.custom(context, msg, resources.getDrawable(es.dmoral.toasty.R.drawable.ic_info_outline_white_48dp), Color.WHITE,
                        resources.getColor(R.color.infoColor), Toast.LENGTH_SHORT, true, true).show();
                break;
            case WARN:
                Toasty.custom(context, msg, resources.getDrawable(es.dmoral.toasty.R.drawable.ic_error_outline_white_48dp), Color.WHITE,
                        resources.getColor(R.color.warnColor), Toast.LENGTH_SHORT, true, true).show();
                break;
            default:
                Toasty.custom(context, msg, resources.getDrawable(es.dmoral.toasty.R.drawable.ic_check_white_48dp), Color.WHITE,
                        resources.getColor(R.color.successColor), Toast.LENGTH_SHORT, true, true).show();
                break;

        }
    }
}
