package com.xujl.utilslibrary.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.WindowManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/20.
 * 系统工具类
 */
public class SystemTool {
    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
    //获取软件版本号
    public static String getAPKVersion (Context applicationContext) {
        try {
            PackageInfo info =
                    applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }
    //拨打电话
    public static void callPhone (Context context, String num) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    // 获取栈顶activity名字
    public static String getTaskActivityName (Context applicationContext) {
        ActivityManager manager = (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity).toString();
        } else {
            return "";
        }
    }
    /**
     * 判断当前应用的是否为前台task
     *
     * @param context
     * @return
     * @author dujinyang
     */
    public static boolean isAppForgroud (Context context) {
        if (context != null) {
            String packName = context.getPackageName();
            List<ActivityManager.RunningTaskInfo> rTasks = getRunningTask(context, 1);
            ActivityManager.RunningTaskInfo task = rTasks.get(0);
            return packName.equalsIgnoreCase(task.topActivity.getPackageName());
        }
        return false;
    }
    /**
     * 判断当前应用的是否为后台task
     *
     * @param context
     * @return
     * @author dujinyang
     */
    public static boolean isAppBackgroud (Context context) {
        if (context != null) {
            String packName = context.getPackageName();
            List<ActivityManager.RunningTaskInfo> rTasks = getRunningTask(context, 1);
            ActivityManager.RunningTaskInfo task = rTasks.get(0);
            return !packName.equalsIgnoreCase(task.topActivity.getPackageName());
        }
        return false;
    }
    /**
     * 判断当前的activity是否为top activity @author dujinyang
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean isTopActivity (Context context, String className) {
        List<ActivityManager.RunningTaskInfo> rTasks = getRunningTask(context, 1);
        for (ActivityManager.RunningTaskInfo task : rTasks) {
            Log.d("SystemUtils", "isTopActivity:" + task.topActivity.getClassName()
                    + "|" + className);
            if (task.topActivity.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }
    public static List<ActivityManager.RunningTaskInfo> getRunningTask (Context context, int num) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> rTasks = am.getRunningTasks(1);
            return rTasks;
        }
        return null;
    }
    /**
     * 获取图片uri
     *
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri (Context context, java.io.File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid) {//得到当前应用{
                return info.processName;//返回包名
            }
        }
        return "";
    }
    /**
     * uri转绝对路径
     *
     * @param selectedVideoUri
     * @param contentResolver
     * @return
     */
    public static String getFilePathFromContentUri (Uri selectedVideoUri,
                                                    ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static int[] getScreenSize(Activity activity){
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width,height};
    }

        /**
         * 获取apk程序信息[packageName,versionName...]
         *
         * @param context Context
         * @param path    apk path
         */
        public static PackageInfo getApkInfo(Context context, String path) {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                //String packageName = info.packageName;
                //String version = info.versionName;
                //Log.d(TAG, "packageName:" + packageName + ";version:" + version);
                //String appName = pm.getApplicationLabel(appInfo).toString();
                //Drawable icon = pm.getApplicationIcon(appInfo);//得到图标信息
                return info;
            }
            return null;
        }

    /**
     * 获取应用sha1
     * @param context
     * @return
     */
    public static String getAppSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
