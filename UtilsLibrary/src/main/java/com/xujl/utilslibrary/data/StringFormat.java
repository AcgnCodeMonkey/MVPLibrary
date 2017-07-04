package com.xujl.utilslibrary.data;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.xujl.utilslibrary.view.ViewTool;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;
import java.util.List;

/**
 * 数字处理工具类
 */
public class StringFormat {
    /* 字符串检测为空时需要返回何种类型的值*/
    public static final int ZERO = 0;
    public static final int NULL = 1;
    public static final int LINE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ZERO, NULL, LINE})
    public @interface modeInterface {
    }

    /**
     * 检测null
     * @param str
     * @param nullValue
     * @return
     */
    public static String checkNull (String str, @modeInterface int nullValue) {
        String defaultResult = "";
        switch (nullValue) {
            case ZERO:
                defaultResult = "0";
                break;
            case NULL:
                defaultResult = "";
                break;
            case LINE:
                defaultResult = "---";
                break;
        }
        return (TextUtils.isEmpty(str)) ? defaultResult : str;
    }

    public static float floatOf (String num) {
        if (ViewTool.isEmpty(num)) {
            return 0f;
        }
        float result = 0f;
        try {
            result = Float.valueOf(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double doubleOf (String num) {
        if (ViewTool.isEmpty(num)) {
            return 0;
        }
        double result = 0f;
        try {
            result = Double.valueOf(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long longOf (String num) {
        if (ViewTool.isEmpty(num)) {
            return 0L;
        }
        long result = 0L;
        try {
            result = Long.valueOf(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int intOf (String num) {
        if (ViewTool.isEmpty(num)) {
            return 0;
        }
        int result = 0;
        try {
            result = Integer.valueOf(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 四舍五入.传入需要保留的小数位数
    public static String FourHomesFive (String num, int code) {
        if (!ViewTool.isEmpty(num)) {
            try {
                BigDecimal setScale = new BigDecimal(num).setScale(code, BigDecimal.ROUND_HALF_UP);
                String v = String.valueOf(setScale);
//                if (v.endsWith(".00")) {
//                    v = v.replace(".00", "");
//                }
                if ("0.00".equals(v)) {
                    return "0";
                }
                return v;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "0";
            }
        }
        return "0";
    }
    //遍历集合工具类
    public static <T> void forList (List<T> list, ForListCallBack<T> f) {
        if (list == null)
            return;
        for (int i = 0, length = list.size(); i < length; i++) {
            f.data(i, list.get(i));
        }
    }

    public interface ForListCallBack<T> {
        void data (int index, T t);
    }
}
