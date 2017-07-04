package com.xujl.utilslibrary.system;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;



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
 */

/**
 * 启动application
 */
public class StartUpApplication extends MultiDexApplication {
    protected AssistService mService;
    protected static boolean IS_DUBUG;//是否dubug模式
    @Override
    public void onCreate () {
        super.onCreate();
        //初始化全局多线程服务
        bindService(new Intent(getApplicationContext(), AssistService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public static boolean isDubug () {
        return IS_DUBUG;
    }

    protected void setDubug (boolean dubug) {
        IS_DUBUG = dubug;
    }

    @Override
    protected void attachBaseContext (Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected (ComponentName componentName, IBinder iBinder) {
            try {
                mService = ((AssistService.AssistBinder) iBinder).getService();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected (ComponentName componentName) {

        }
    };

    public AssistService getService () {
        return mService;
    }


}
