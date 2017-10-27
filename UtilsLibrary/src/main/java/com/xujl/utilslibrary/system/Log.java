package com.xujl.utilslibrary.system;


/**
 * Created by Administrator on 2016/7/28.
 * log工具类
 */
public class Log {
    public static boolean isDubug = true;

    public static void i (String tag, String msg) {
        if (isDubug) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void d (String tag, String msg) {
        if (isDubug) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void w (String tag, String msg) {
        if (isDubug) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w (String tag, String msg, Throwable throwable) {
        if (isDubug) {
            android.util.Log.w(tag, msg, throwable);
        }
    }

    public static void v (String tag, String msg) {
        if (isDubug) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void e (String tag, String msg) {
        if (isDubug) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e (String tag, String msg, Throwable throwable) {
        if (isDubug) {
            android.util.Log.e(tag, msg, throwable);
        }
    }
}
