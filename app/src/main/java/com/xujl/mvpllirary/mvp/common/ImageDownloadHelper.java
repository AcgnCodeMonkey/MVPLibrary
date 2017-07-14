package com.xujl.mvpllirary.mvp.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.xujl.mvpllirary.util.DownloadFilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xujl on 2017/7/10.
 */

public class ImageDownloadHelper {
    private ImageDownLoadCallBack mImageDownLoadCallBack;
    public void downloadImage(Context context, String url, ImageDownLoadCallBack callBack){
        mImageDownLoadCallBack = callBack;
        new Thread(new DownLoadImageRunnable(context, url, new ImageDownLoadCallBack() {
            @Override
            public void onDownLoadSuccess (Bitmap bitmap) {

            }

            @Override
            public void onDownLoadFailed () {

            }
        })).start();
    }

    private class DownLoadImageRunnable implements Runnable {
        private String url;
        private Context context;
        private ImageDownLoadCallBack callBack;
        private File currentFile;

        public DownLoadImageRunnable (Context context, String url, ImageDownLoadCallBack callBack) {
            this.url = url;
            this.callBack = callBack;
            this.context = context;
        }

        @Override
        public void run () {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
                if (bitmap != null) {
                    // 在这里执行图片保存方法
                    saveImageToGallery(context, bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bitmap != null && currentFile.exists()) {
                    callBack.onDownLoadSuccess(bitmap);
                } else {
                    callBack.onDownLoadFailed();
                }
            }
        }

        public void saveImageToGallery (Context context, Bitmap bmp) {
            // 首先保存图片
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
            String fileName = DownloadFilePath.IMAGE_DOWNLOAD_PATH;
            File appDir = new File(file, fileName);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            fileName = fileName + System.currentTimeMillis() + ".jpg";
            currentFile = new File(appDir, fileName);

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(currentFile);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(new File(currentFile.getPath()))));
        }
    }

    public interface ImageDownLoadCallBack {

        void onDownLoadSuccess (Bitmap bitmap);

        void onDownLoadFailed ();
    }
}
