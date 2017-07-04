package com.xujl.utilslibrary.system;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/17.
 * 后台服务，负责后台开启子线程异步处理某些事务
 */
public class AssistService extends Service {
    //线程池类型
    public static final int SINGLE_TYPE = 0;//单线程类型
    public static final int MORE_TYPE = 1;//容许多条线程的线程池
    private int mExecutorsType = SINGLE_TYPE;//当前线程池类型
    public static final String TAG = "任务";
    private IBinder mBinder = new AssistBinder();
    private LinkedList<TaskCallBack> mTaskQueue;//任务队列
    private ExecutorService mExecutorService;//负责执行任务的线程池
    private ExecutorService mSingleExecutorService = Executors.newSingleThreadScheduledExecutor();//单线程线程池
    private ExecutorService mMoreExecutorService = Executors.newCachedThreadPool();//多线程线程池

    @Nullable
    @Override
    public IBinder onBind (Intent intent) {
        mTaskQueue = new LinkedList<>();//初始化任务队列
        mExecutorService = mSingleExecutorService;//默认使用单线程线程池
        return mBinder;
    }

    public class AssistBinder extends Binder {
        public AssistService getService () {
            return AssistService.this;
        }
    }
    //通过传入的类型参数切换到对应的线程池
    public AssistService changeExecutorsType (@ExecutorsType int mode) {
        if (mode == SINGLE_TYPE) {
            mExecutorService = mSingleExecutorService;
        } else {
            mExecutorService = mMoreExecutorService;
        }
        return this;
    }

    //任务线程
    private class TaskThread extends Thread {
        private TaskCallBack mTask;//任务

        public void setTask (TaskCallBack task) {
            mTask = task;
        }

        @Override
        public void run () {
//            Log.e("活动线程数", String.valueOf(((ThreadPoolExecutor) mExecutorService).getActiveCount()));
            if (mTask != null) {
                synchronized (mTask.mLockList) {
                    mTask.taskContent();//执行任务
                    mTask = null;//清空任务
                }
            }
        }
    }

    //任务分发
    private void taskDistribution () {
        //判断是否还有任务
        if (mTaskQueue.size() != 0) {
            TaskThread taskThread = new TaskThread();
            taskThread.mTask = mTaskQueue.removeFirst();
            mExecutorService.execute(taskThread);
            taskDistribution();
        }
    }

    //添加任务到队列
    public void addTask (@ExecutorsType int mode,TaskCallBack taskInterface) {
        //判断传入的需要使用的线程池类型是否与当前的线程池类型相同，不同就变更线程池类型
        if( mExecutorsType != mode){
            mExecutorsType = mode;
            changeExecutorsType(mode);
        }

        mTaskQueue.addLast(taskInterface);
//        Log.e(TAG, "添加了一个新任务到队列");
        taskDistribution();//分发任务
    }
    //任务回调类
    public static abstract class TaskCallBack {
        private List<Object> mLockList;//需要加锁的数据

        public TaskCallBack (List<Object> lockList) {
            if (lockList == null) {
                mLockList = new ArrayList<>();
            } else {
                mLockList = lockList;
            }
        }

        public abstract void taskContent ();//任务内容执行回调
    }

    @IntDef({SINGLE_TYPE, MORE_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExecutorsType {
    }
}
