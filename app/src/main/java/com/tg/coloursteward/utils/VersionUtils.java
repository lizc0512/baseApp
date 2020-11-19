package com.tg.coloursteward.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * @name ${yuansk}
 * @class name：com.colourlife.safelife.utils
 * @class describe
 * @anthor ${ysk} QQ:827194927
 * @time 2019/1/8 16:23
 * @change
 * @chang time
 * @class describe
 */
public class VersionUtils {

    /**
     * 获取软件版本名
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "1.0.0";
        try {
            // 获取软件版本号，
            versionName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
    /**
     * 获取软件版本号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            // 获取软件版本号，
            versionCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
