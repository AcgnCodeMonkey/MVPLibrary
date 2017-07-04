package com.xujl.utilslibrary.system;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.xujl.utilslibrary.view.ViewTool;

/**
 * 获取网络连接的状态信息工具类
 */

public class InternetState {
    private static  Context sContext;


    public static void setContext (Context context) {
        sContext = context;
    }

    /**
     * 检测网络是否可用
     */
    public static boolean isNetworkConnected () {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) sContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型
     * 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static int getNetworkType () {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) sContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!ViewTool.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

}
