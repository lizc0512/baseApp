package com.tg.coloursteward.utils;

import android.util.Log;

import com.tg.coloursteward.BuildConfig;

/**
 * @name ${lizc}
 * @class name：com.colourlife.safelife.utils
 * @class log通用工具类
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class LogUtils {

    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static void v(String tag, String message) {
        if (DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if (DEBUG) {
            Log.e(tag, message, e);
        }
    }

}
