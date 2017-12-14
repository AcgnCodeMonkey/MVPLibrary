package com.xujl.baselibrary.utils;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.xujl.baselibrary.mvp.port.IBaseModel;

import java.util.Stack;

/**
 * activity管理工具类，用于关闭所有界面
 */
public class ActivityManger implements Application.ActivityLifecycleCallbacks {
    private static volatile ActivityManger sManger;
    /**
     * 单例对象
     */
    private static volatile Stack<Activity> activityStack;

    private ActivityManger () {
    }

    public synchronized static ActivityManger newInstance () {
        if (sManger == null) {
            synchronized (ActivityManger.class) {
                sManger = new ActivityManger();
                activityStack = new Stack<>();
            }
        }
        return sManger;
    }

    public Stack<Activity> getActivityList () {
        return activityStack;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity (Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity () {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity () {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity (Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity (Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity () {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit (Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keepActivityClearOther (Activity activity) {
        Activity retainActivity = activity;
        for (Activity a : activityStack) {
            if (activity != a) {
                a.finish();
            }
        }
        activityStack.clear();
        if (retainActivity != null) {
            activityStack.add(retainActivity);
        }
    }

    /**
     * 保留指定类名的Activity,并关闭其他所有的activity
     */
    public void keepActivity (Class<?> cls) {
        Activity retainActivity = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                retainActivity = activity;
            } else {
                activity.finish();
            }
        }
        activityStack.clear();
        if (retainActivity != null) {
            activityStack.add(retainActivity);
        }


    }

    /**
     * 保留指定类名的Activity,并关闭其他所有的activity
     */
    public <M extends IBaseModel> void keepActivity (String cls) {
        Activity retainActivity = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().toString().equals(cls)) {
                retainActivity = activity;
            } else {
                activity.finish();
            }
        }
        activityStack.clear();
        if (retainActivity != null) {
            activityStack.add(retainActivity);
        }


    }

    /**
     * 清空栈内指定activity
     * @param activity
     */
    public void removeActivity (Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    @Override
    public void onActivityCreated (Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted (Activity activity) {

    }

    @Override
    public void onActivityResumed (Activity activity) {

    }

    @Override
    public void onActivityPaused (Activity activity) {

    }

    @Override
    public void onActivityStopped (Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState (Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed (Activity activity) {
        removeActivity(activity);
    }
}
