package com.xujl.mvpllirary.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by xujl on 2017/7/10.
 */

public class DownloadFilePath {
    public static final String IMAGE_DOWNLOAD_PATH = getSDPath() + "/mvpLibrary/";

    public static String getSDPath () {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
