package com.xujl.baselibrary.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by xujl on 2017/4/17.
 * 权限帮助类，用于获取权限对应名称
 */

public class PermissionsHelper {
    private static final Map<String, String[]> PERMISSIONS_MAP;

    static {
        PERMISSIONS_MAP = new HashMap<>();
        PERMISSIONS_MAP.put("位置权限", new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        PERMISSIONS_MAP.put("SD卡读取权限", new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        PERMISSIONS_MAP.put("拨打电话权限", new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_PHONE_STATE"
                , "android.permission.CALL_PHONE", "android.permission.WRITE_CALL_LOG", "android.permission.USE_SIP"
                , "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.ADD_VOICEMAIL"});
        PERMISSIONS_MAP.put("相机使用权限", new String[]{"android.permission.CAMERA"});
        PERMISSIONS_MAP.put("短信读取权限", new String[]{"android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH"
                , "android.permission.RECEIVE_MMS", "android.permission.RECEIVE_SMS"
                , "android.permission.SEND_SMS", "android.permission.READ_CELL_BROADCASTS"});
        PERMISSIONS_MAP.put("通讯录读取权限", new String[]{"android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"
                , "android.permission.READ_CONTACTS"});
        PERMISSIONS_MAP.put("录音使用权限", new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
    }

    /**
     * 获取未授权的权限中文名称字符串
     *
     * @param context
     * @param permissions
     * @return
     */
    public static String getNoPermissionsStrings (Context context, List<String> permissions) {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ListUtils.isEmpty(permissions)) {
            Log.e("PermissionsHelper", "hasPermissions: API version < M, returning true by default");
            // DANGER ZONE!!! Changing this will break the library.
            return "";
        }
        StringBuilder resultString = new StringBuilder();
        for (String perm : permissions) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) ==
                    PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                final String permissionName = getPermissionName(perm);
                //防止重复显示同一权限
                if (resultString.toString().contains(permissionName)) {
                    continue;
                }
                resultString.append(permissionName);
                resultString.append(",");
            }
        }
        if ( resultString.length() != 0) {
            return resultString.substring(0,resultString.length()-1);
        }
        return resultString.toString();
    }

    /**
     * 获取未授权的权限中文名称字符串
     *
     * @param context
     * @param permissions
     * @return
     */
    public static String getNoPermissionsStrings (Context context, String[] permissions) {
        return getNoPermissionsStrings(context, Arrays.asList(permissions));
    }

    /**
     * 获取危险权限中文名
     *
     * @param perm
     * @return
     */
    public static String getPermissionName (String perm) {
        final Set<String> keySet = PERMISSIONS_MAP.keySet();
        for (String key : keySet) {
            final String[] permissions = PERMISSIONS_MAP.get(key);
            for (String permission : permissions) {
                if (permission.equals(perm)) {
                    return key;
                }
            }
        }
        return "";
    }
}
