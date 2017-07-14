package com.xujl.datalibrary.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 图片上传实体类
 */
public class FormImage {
    // 文件名
    private String mFileName;
    // 文件的 mime，需要根据文档查询
    private String mMime;
    // 需要上传的图片资源
    private Bitmap mBitmap;
    private File file;
    private int maxSize = 500 * 1024;//允许的最大尺寸,byte


    @SuppressWarnings("unused")
    public FormImage (String path) {
        super();
        this.mBitmap = BitmapFactory.decodeFile(path);
        this.file = new File(path);
        Date date = new Date();
        long time = date.getTime();
        mFileName = time + path;
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath() + file.getName());
        // 获取文件MIME类型
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            mMime = mime.getMimeTypeFromExtension(extension);
        }
    }

    public String getName () {
        // return mName;
        return "file[]";
    }

    public String getFileName () {

        return mFileName;
    }

    // 对图片进行二进制转换
    public byte[] getValue () {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        return bos.toByteArray();
    }

    // 因为我知道是 png 文件，所以直接根据文档查的
    public String getMime () {
        return mMime;
    }

    public File getFile () {
        return file;
    }

    public File getCompressFile () {
        final int byteCount = mBitmap.getByteCount();
        if (byteCount > maxSize) {
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
                mBitmap.compress(Bitmap.CompressFormat.JPEG, (int) (getCompressRate(byteCount) * 100), write);
                write.flush();
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.file = file;
        }
        return file;
    }

    //获取压缩率
    private float getCompressRate (int byteCount) {
        if (byteCount < maxSize) {
            return 1;
        }
        return 1- maxSize/byteCount;
    }

    public void setFile (File file) {
        this.file = file;
    }

}
