package com.xujl.mvpllirary.mvp.common;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.baozi.Zxing.CaptureActivity;
import com.baozi.Zxing.ZXingConstants;
import com.xujl.baselibrary.mvp.common.BaseHelper;
import com.xujl.baselibrary.mvp.presenter.BaseFragmentPresenter;

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

public class QRScanHelper extends BaseHelper {
    public void openScanner (BaseFragmentPresenter presenter) {
        Intent intent = new Intent(presenter.exposeContext(), CaptureActivity.class);
        //是不是显示历史记录按钮
        intent.putExtra(ZXingConstants.ScanIsShowHistory, false);
        presenter.startActivityForResult(intent, ZXingConstants.ScanRequestCode);
    }

    public String onActivityResult (int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return null;
        }
        final Bundle bundle = data.getExtras();
        if (bundle == null) {
            return null;
        }
        String result = null;
        switch (requestCode) {
            case ZXingConstants.ScanRequestCode:

                if (resultCode == ZXingConstants.ScanRequestCode) {
                    /**
                     * 拿到解析完成的字符串
                     */
                    result = bundle.getString(ZXingConstants.ScanResult);
                } else if (resultCode == ZXingConstants.ScanHistoryResultCode) {
                    /**
                     * 历史记录
                     */
                    String resultHistory = bundle.getString(ZXingConstants.ScanHistoryResult);
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
