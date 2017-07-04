package com.xujl.utilslibrary.data;


import com.xujl.utilslibrary.view.ViewTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xujl on 2017/4/5.
 */

public class MatcherUtils {
    /**
     * @param str
     * @return 是否仅包含数字(不包含小数点)
     */
    public static boolean isOnlyHasNumber (String str) {
        return !ViewTool.isEmpty(str) && str.matches("[0-9]*");
    }

    /**
     * @param str
     * @return 是否是一个数字(包含小数)
     */
    public static boolean isNumber (String str) {
        return !ViewTool.isEmpty(str) && str.matches("^0(\\.\\d(\\d+)?)?$|^[-]?[0-9](\\d+)?(\\.\\d(\\d+)?)?$");
    }

    /**
     * @param str
     * @return 是否是一个合法邮箱
     */
    public static boolean isEmail (String str) {
        return !ViewTool.isEmpty(str) && str.matches("^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\\\.){1,3}[a-zA-z\\\\-]{1,}$");
    }

    /**
     * @param str
     * @return 是否是一个合法手机号
     */
    public static boolean isPhoneNumber (String str) {
        return !ViewTool.isEmpty(str) && str.matches("^(0|86|17951)?((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\\\D])|(18[0-9]))\\\\d{8}$");
    }

    /**
     * 从短信字符串提取验证码
     * @param body      短信内容
     * @param length 验证码的长度 一般6位或者4位
     * @return 接取出来的验证码
     */
    public static String getCheckedCode (String body, int length) {
        // 首先([a-zA-Z0-9]{YZMLENGTH})是得到一个连续的六位数字字母组合
        // (?<![a-zA-Z0-9])负向断言([0-9]{YZMLENGTH})前面不能有数字
        // (?![a-zA-Z0-9])断言([0-9]{YZMLENGTH})后面不能有数字出现

        //  获得数字字母组合
        //    Pattern p = Pattern   .compile("(?<![a-zA-Z0-9])([a-zA-Z0-9]{" + YZMLENGTH + "})(?![a-zA-Z0-9])");
        //  获得纯数字
        Pattern p = Pattern.compile("(?<![0-9])([0-9]{" + length + "})(?![0-9])");
        Matcher m = p.matcher(body);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
