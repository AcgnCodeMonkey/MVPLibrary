package com.xujl.mvpllirary.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by xujl on 2017/6/16.
 * 高斯模糊工具类
 */

public class FastBlurUtil {
    /**
     * 根据imagepath获取bitmap
     */
    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     * <p>
     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
     * <p>
     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
     * <p>
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param url
     * @return
     */
    public static int IO_BUFFER_SIZE = 2 * 1024;


    public static Bitmap getUrlBitmap(String url, int scaleRatio) {


        int blurRadius = 8;//通常设置为8就行。
        if (scaleRatio <= 0) {
            scaleRatio = 10;
        }




        Bitmap originBitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            originBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                        originBitmap.getWidth() / scaleRatio,
                        originBitmap.getHeight() / scaleRatio,
                        false);
                Bitmap blurBitmap = doBlur(scaledBitmap, blurRadius, true);
            return blurBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    /**
     * 更改透明度
     * @param sourceImg
     * @param number 0-100
     * @return
     */
    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number){
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];

        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg

                .getWidth(), sourceImg.getHeight());// 获得图片的ARGB值

        number = number * 255 / 100;

        for (int i = 0; i < argb.length; i++) {

            argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);

        }

        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg

                .getHeight(), Bitmap.Config.ARGB_8888);

        return sourceImg;
    }


    //    把本地图片毛玻璃化
    public static Bitmap toBlur(Bitmap originBitmap, int scaleRatio) {
        //        int scaleRatio = 10;
        // 增大scaleRatio缩放比，使用一样更小的bitmap去虚化可以到更好的得模糊效果，而且有利于占用内存的减小；
        int blurRadius = 8;//通常设置为8就行。
        //增大blurRadius，可以得到更高程度的虚化，不过会导致CPU更加intensive


       /* 其中前三个参数很明显，其中宽高我们可以选择为原图尺寸的1/10；
        第四个filter是指缩放的效果，filter为true则会得到一个边缘平滑的bitmap，
        反之，则会得到边缘锯齿、pixelrelated的bitmap。
        这里我们要对缩放的图片进行虚化，所以无所谓边缘效果，filter=false。*/
        if (scaleRatio <= 0) {
            scaleRatio = 10;
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                originBitmap.getWidth() / scaleRatio,
                originBitmap.getHeight() / scaleRatio,
                false);
        Bitmap blurBitmap = doBlur(scaledBitmap, blurRadius, true);
        return blurBitmap;
    }


    public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }


        if (radius < 1) {
            return (null);
        }


        int w = bitmap.getWidth();
        int h = bitmap.getHeight();


        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);


        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;


        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];


        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }


        yw = yi = 0;


        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;


        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;


            for (x = 0; x < w; x++) {


                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];


                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;


                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];


                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];


                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];


                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);


                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];


                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;


                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];


                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];


                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];


                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;


                sir = stack[i + radius];


                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];


                rbs = r1 - Math.abs(i);


                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;


                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }


                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];


                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;


                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];


                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];


                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];


                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];


                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];


                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;


                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];


                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];


                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];


                yi += w;
            }
        }


        bitmap.setPixels(pix, 0, w, 0, 0, w, h);


        return (bitmap);
    }

    /**
     * 创建纯色图片
     * @param width
     * @param height
     * @param colorString "#FF0000"
     * @return
     */
    public static Bitmap creatBitmapForColor(int width,int height,String colorString){
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.parseColor(colorString));//填充颜色
        return bitmap;
    }
}
