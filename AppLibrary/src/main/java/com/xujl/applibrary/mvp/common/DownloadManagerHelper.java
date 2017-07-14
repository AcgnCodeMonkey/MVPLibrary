package com.xujl.applibrary.mvp.common;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.xujl.baselibrary.mvp.common.BaseHelper;

import java.io.File;

/**
 * Created by xujl on 2017/6/7.
 * 文件下载帮助类，调用系统downloadManager下载文件
 */

public class DownloadManagerHelper extends BaseHelper{
    private DownloadManager mManager;
    private DownloadManager.Request mRequest;
    private Context mContext;
    private DownloadCallback mCallback;//下载回调
    private String mDownloadFlieName;//下载的文件名，不应该包含路径信息
    private BroadcastReceiver mReceiver;//下载完成广播接收器
    private long mDownloadId;//文件下载id

    public DownloadManagerHelper (Context context, String downloadUrl) {
        mContext = context;
        mManager = (DownloadManager) mContext.getSystemService(Activity.DOWNLOAD_SERVICE);
        mRequest = new DownloadManager.Request(Uri.parse(downloadUrl));
    }

    /**
     * 使用此构造器时应注意设置时应该优先设置下载url，防止mRequest为空
     *
     * @param context
     */
    public DownloadManagerHelper (Context context) {
        mContext = context;
        mManager = (DownloadManager) mContext.getSystemService(Activity.DOWNLOAD_SERVICE);
    }

    /**
     * 设置下载回调,只有当调用了此方法且设置的回调不为空时才去注册下载广播接收器
     *
     * @param callback
     * @return
     */
    public DownloadManagerHelper setCallback (DownloadCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mReceiver = new DownloadReceiver();
            mContext.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        return this;
    }

    /**
     * 设置下载下载地址
     *
     * @param url
     * @return
     */
    public DownloadManagerHelper setDownloadUrl (String url) {
        mRequest = new DownloadManager.Request(Uri.parse(url));
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
        mRequest.setDestinationInExternalFilesDir(mContext,pathName, downloadFlieName);
        return this;
    }

    /**
     * 设置下载的通知标题
     *
     * @param title
     * @return
     */
    public DownloadManagerHelper setNotificationTitle (String title) {
        mRequest.setTitle(title);
        return this;
    }

    /**
     * 设置下载的通知描述
     *
     * @param description
     * @return
     */
    public DownloadManagerHelper setNotificationDescription (String description) {
        mRequest.setDescription(description);
        return this;
    }

    /**
     * 开始下载并保存下载id
     */
    public void start () {
        mDownloadId = mManager.enqueue(mRequest);
    }

    /**
     * 设置下载通知的可见状态
     *
     * @param visibility DownloadManager.Request.VISIBILITY_HIDDEN
     * @return
     */
    public DownloadManagerHelper setNotificationVisibility (int visibility) {
        mRequest.setNotificationVisibility(visibility);
        return this;
    }

    /**
     * 设置下载的文件类型
     *
     * @param mimeType
     * @return
     */
    public DownloadManagerHelper setMimeType (String mimeType) {
        mRequest.setMimeType(mimeType);
        return this;
    }

    /**
     * 通过下载id获取下载文件的uri
     *
     * @param downloadId
     * @return
     */
    public Uri getUriForDownloadedFile (long downloadId) {
        return mManager.getUriForDownloadedFile(downloadId);
    }

    /**
     * 通过下载id删除一个已经下载的文件
     *
     * @param downloadId
     */
    public void remove (long downloadId) {
        mManager.remove(downloadId);
    }

    /**
     * 获取下载文件状态信息
     *
     * @param downloadId
     * @return
     */
    public int getDownloadStatus (long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        if (query != null) {
            Cursor c = mManager.query(query);
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                    }
                } finally {
                    c.close();
                }
            }
        }
        return -1;
    }

    /**
     * 此方法应在适当位置调用，防止因为广播引起的内存泄漏
     */
    public void onDestroy () {
        if (mReceiver != null) {
            mContext.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    public long getDownloadId () {
        return mDownloadId;
    }

    /**
     * 通过query查询下载状态，包括已下载数据大小，总大小，下载状态
     *
     * @param downloadId
     * @return
     */
    public int[] getBytesAndStatus (long downloadId) {
        int[] bytesAndStatus = new int[]{
                -1, -1, 0
        };
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = null;
        try {
            cursor = mManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载文件大小
                bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //下载文件的总大小
                bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //下载状态
                bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bytesAndStatus;
    }
    public void unRegisterReceiver(Context context){
        if(mReceiver != null){
            context.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
    public class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive (Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                if (mCallback != null) {
                    mCallback.complete(new File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            , mDownloadFlieName));
                    unRegisterReceiver(mContext);
                }
            }
        }
    }

    public interface DownloadCallback {
        /**
         * 下载完成
         *
         * @param file
         */
        void complete (File file);
    }

}
