package com.xujl.mvpllirary.mvp.common;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.baozi.Zxing.CaptureActivity;
import com.baozi.Zxing.ZXingConstants;
import com.xujl.baselibrary.mvp.common.BaseHelper;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/4/25.
 */

public class QRScanHelper extends BaseHelper{
    public  void openScanner (Activity activity) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        //是不是显示历史记录按钮
        intent.putExtra(ZXingConstants.ScanIsShowHistory, false);
        activity.startActivityForResult(intent, ZXingConstants.ScanRequestCode);
    }

    public  String onActivityResult (int requestCode, int resultCode, Intent data) {
        String result = null;
        switch (requestCode) {
            case ZXingConstants.ScanRequestCode:

                if (resultCode == ZXingConstants.ScanRequestCode) {
                    /**
                     * 拿到解析完成的字符串
                     */
                    result = data.getStringExtra(ZXingConstants.ScanResult);
                } else if (resultCode == ZXingConstants.ScanHistoryResultCode) {
                    /**
                     * 历史记录
                     */
                    String resultHistory = data.getStringExtra(ZXingConstants.ScanHistoryResult);
                    if (!TextUtils.isEmpty(resultHistory)) {
                        //自己实现历史页面
//                        startActivity(new Intent(MainActivity.this,HistoryActivity.class));
                    }
                }
                break;
                default:
                    break;
        }
        return result;
    }
}
