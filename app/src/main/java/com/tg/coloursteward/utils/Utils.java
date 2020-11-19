package com.tg.coloursteward.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {


    public static Bitmap scaleBitmap(Bitmap bm, int pixel) {
        int srcHeight = bm.getHeight();
        int srcWidth = bm.getWidth();
        if (srcHeight > pixel || srcWidth > pixel) {
            float scale_y = 0;
            float scale_x = 0;
            if (srcHeight > srcWidth) {
                scale_y = ((float) pixel) / srcHeight;
                scale_x = scale_y;
            } else {
                scale_x = ((float) pixel) / srcWidth;
                scale_y = scale_x;
            }

            Matrix matrix = new Matrix();
            matrix.postScale(scale_x, scale_y);
            Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, srcWidth, srcHeight, matrix, true);
            return dstbmp;
        } else {
            return Bitmap.createBitmap(bm);
        }
    }


    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^0?(13[0-9]|15[012356789]|18[012356789]|14[57]|17[0678])[0-9]{8}"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isSpecialharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    //获取当前屏幕宽度
    public static int getDeviceWith(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }

    //获取当前屏幕高度
    public static int getDeviceHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        return height;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static Map transformJsonToMap(JSONObject jsonObject) {
        Map<String, Object> params = new HashMap<String, Object>();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {

            String key = keys.next().toString();
            Object value = jsonObject.opt(key);
            params.put(key, value);
        }

        return params;

    }


    public static String setMD5(String string) {
        MessageDigest md5;
        StringBuffer sb = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes("UTF-8"));
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                int temp = 0xFF & b[i];
                String s = Integer.toHexString(temp);
                if (temp <= 0x0F) {
                    s = "0" + s;
                }
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private static long disDiff = 0;

    public static String trimTelNum(String telNum) {
        if (telNum == null || "".equals(telNum)) {
            System.out.println("trimTelNum is null");
            return null;
        }
        telNum = telNum.replace("-", "");
        telNum = telNum.replace(" ", "");
        if (substring(telNum, 0, 4).equals("0086"))
            telNum = substring(telNum, 4);
        else if (substring(telNum, 0, 3).equals("+86"))
            telNum = substring(telNum, 3);
        else if (substring(telNum, 0, 5).equals("00186"))
            telNum = substring(telNum, 5);
        else if (substring(telNum, 0, 2).equals("86"))
            telNum = substring(telNum, 2);
        return telNum;
    }

    /**
     * 截取字符串
     *
     * @param s
     * @param from
     * @return
     */
    protected static String substring(String s, int from) {
        try {
            return s.substring(from);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    protected static String substring(String s, int from, int len) {
        try {
            return s.substring(from, from + len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
        Matcher m = pattern.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    // 获取一个字符串中的数字部分
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static int getNumericStartPosition(String str) {
        int pos = -1;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static int getNumericEndPosition(String str) {
        int pos = str.length() - 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isDigit(str.charAt(i))) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static SpannableString getSpannable(String content) {
        SpannableString spannableString = new SpannableString(content);
        try {
            int numStartPos = getNumericStartPosition(content);
            if (numStartPos != -1) {
                int numEndtPos = getNumericEndPosition(content);
                int totalLength = content.length();
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7E8B95")), 0, numStartPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f25845")), numStartPos, numEndtPos + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7E8B95")), numEndtPos + 1, totalLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7E8B95")), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception e) {
            spannableString = new SpannableString(content);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7E8B95")), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
    /**
     * 隐藏键盘
     *
     * @param v
     */
    public static void hideKeyboard(final View v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
            }
        }, 10);
    }
}
