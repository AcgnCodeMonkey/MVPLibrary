package com.xujl.utilslibrary.data;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/12.
 * 图片处理工具类
 */
public class ImageUtils {

    public static void saveJPGE_After (Bitmap bitmap,String path,String fileName) throws IOException {
        File destDir = new File(path);
        //判断需要保存的目录是否存在
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        //默认保存类型为jpeg
        File file = new File(path,fileName
                );
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = new FileOutputStream(file);
        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
            out.flush();
            out.close();
        }
    }
    public static void saveJPGE_After (Bitmap bitmap,String path) throws IOException {
        File destDir = new File(path);
        //判断需要保存的目录是否存在
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        //默认保存类型为jpeg
        File file = new File(path
        );
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = new FileOutputStream(file);
        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
            out.flush();
            out.close();
        }
    }
}
