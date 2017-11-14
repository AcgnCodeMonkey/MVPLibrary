package com.xujl.baselibrary;

import android.util.Log;

/**
 * Created by xujl on 2017/11/9.
 */

public class Logger {
    private static boolean sIsDebug;

    public static void init (boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void e (String tag, String e) {
        if (sIsDebug) {
            Log.e(tag, e);
        }
    }
}
