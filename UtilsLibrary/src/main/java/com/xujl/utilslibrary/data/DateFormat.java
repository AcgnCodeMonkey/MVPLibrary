package com.xujl.utilslibrary.data;

import android.annotation.SuppressLint;

import com.xujl.utilslibrary.system.Log;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressLint("SimpleDateFormat")
public class DateFormat {

    /**
     * 把标准时间转换成毫秒时间
     */
    public static String getLongTime (String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time).getTime() + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *   获取当前时间，需要传入格式
     */
    public static String getNowTime (String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            // 获取当前时间
            Date curDate = new Date(System.currentTimeMillis());
            return formatter.format(curDate);
        } catch (Exception e) {
            Log.e("日期格式化异常", e.toString());
        }
        return "";
    }

    /**
     * 把毫秒时间转换成指定格式时间
     */
    public static String getDate (String time, String format) {
        try {
            final long aLong = Long.parseLong(time);
            if (time == null || "".equals(time)) {
                return "";
            } else {
                if (time.length() < 13) {
                    final int length = time.length();
                    for (int i = 0; i < (13 - length); i++) {
                        time += "0";
                    }
                } else if (time.length() > 13) {
                    time = time.substring(0, 13);
                }
                Date d = new Date(aLong);
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(d);
            }
        } catch (Exception e) {
            Log.e("日期格式化异常", e.toString());
            return time;
        }
    }

    /**
     * 获取星期几
     * @param time
     * @return
     */
    public static String getWeek (long time) {
        Calendar calender = new GregorianCalendar();
        calender.setTimeInMillis(time);
        int i = calender.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }
}
