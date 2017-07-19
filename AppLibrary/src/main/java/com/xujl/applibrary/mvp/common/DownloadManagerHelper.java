package com.xujl.applibrary.mvp.common;

import android.content.Context;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.xujl.baselibrary.mvp.common.BaseHelper;

/**
 * Created by xujl on 2017/6/7.
 * 文件下载帮助类，调用系统downloadManager下载文件
 */

public class DownloadManagerHelper extends BaseHelper{
    private DownloadCallback mCallback;//下载回调
    private String mDownloadFlieName;//下载的文件名，不应该包含路径信息
    private long mDownloadId;//文件下载id
    private BaseDownloadTask downloadTask;
    public DownloadManagerHelper newTask(Context context){
        FileDownloader.setup(context);
        return this;
    }

    /**
     * 设置下载回调,只有当调用了此方法且设置的回调不为空时才去注册下载广播接收器
     *
     * @param callback
     * @return
     */
    public DownloadManagerHelper setCallback (DownloadCallback callback) {
        mCallback = callback;
        return this;
    }

    /**
     * 设置下载下载地址
     *
     * @param url
     * @return
     */
    public DownloadManagerHelper setDownloadUrl (String url) {
        downloadTask = FileDownloader.getImpl().create(url);
        return this;
    }

    /**
     * 设置下载的文件名（不要包含路径信息）
     *
     * @param downloadFlieName
     * @return
     */
    public DownloadManagerHelper setDownloadFlieName (String pathName,String downloadFlieName) {
        mDownloadFlieName = downloadFlieName;
        downloadTask.setPath(pathName+downloadFlieName);
        return this;
    }

    /**
     * 设置下载的通知标题
     *
     * @param title
     * @return
     */
    public DownloadManagerHelper setNotificationTitle (String title) {
        return this;
    }

    /**
     * 设置下载的通知描述
     *
     * @param description
     * @return
     */
    public DownloadManagerHelper setNotificationDescription (String description) {
        return this;
    }

    /**
     * 开始下载并保存下载id
     */
    public void start () {
        mDownloadId = downloadTask.getId();
        downloadTask.setListener(mCallback).start();
    }

    /**
     * 设置下载通知的可见状态
     *
     * @param visibility DownloadManager.Request.VISIBILITY_HIDDEN
     * @return
     */
    public DownloadManagerHelper setNotificationVisibility (int visibility) {
        return this;
    }

    /**
     * 设置下载的文件类型
     *
     * @param mimeType
     * @return
     */
    public DownloadManagerHelper setMimeType (String mimeType) {
        return this;
    }


    /**
     * 通过下载id删除一个已经下载的文件
     *
     * @param downloadId
     */
    public void remove (long downloadId) {
    }


    /**
     * 此方法应在适当位置调用，防止因为广播引起的内存泄漏
     */
    public void onDestroy () {
    }

    public long getDownloadId () {
        return mDownloadId;
    }


    public static abstract class  DownloadCallback extends FileDownloadListener{
    }

}
