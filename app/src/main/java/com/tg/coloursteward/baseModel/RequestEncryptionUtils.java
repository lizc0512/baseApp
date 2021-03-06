package com.tg.coloursteward.baseModel;

import android.content.Context;
import android.text.TextUtils;

import com.tg.coloursteward.application.BaseApplication;
import com.tg.coloursteward.constants.Contants;
import com.tg.coloursteward.constants.SpConstants;
import com.tg.coloursteward.utils.DES;
import com.tg.coloursteward.utils.MD5;
import com.tg.coloursteward.utils.SharedPreferencesUtils;
import com.tg.coloursteward.utils.TokenUtils;
import com.tg.coloursteward.utils.VersionUtils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 创建时间 : 2017/7/10.
 * 编写人 :  ${yuansk}
 * 功能描述: 请求数据的加密
 * 版本:
 */

public class RequestEncryptionUtils {
    /**
     * 请求成功
     ***/
    public static final int responseSuccess = 200;

    /***请求Combile的post加密 通过type支持多域名***/
    public static String getRequestUrl(Context context, int type, String urlString) {
        String finalUrl = "";
        if (type == 0) {
            finalUrl = Contants.URl.URL_NEW + urlString; //oauthtoken接口
        } else if (type == 1) {
            finalUrl = Contants.URl.URL_CPMOBILE + urlString;//cpmobile接口
        } else if (type == 2) {
            finalUrl = Contants.URl.URL_OAUTH2 + urlString;//oauth2接口
        } else if (type == 3) {
            finalUrl = Contants.URl.SINGLE_DEVICE + urlString;//单设备接口
        } else if (type == 4) {
            finalUrl = Contants.URl.URL_ICETEST + urlString;//ICE接口
        }else if (type == 5) {
            finalUrl = Contants.URl.URL_ICESTAFF + urlString;//ICESTAFF接口
        }
        return finalUrl;
    }

    public static String getMapToString(Map<String, Object> paramsMap) {
        if (null == paramsMap) {
            return "";
        } else {
            Iterator<String> it = paramsMap.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = String.valueOf(paramsMap.get(key));
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            if (null == sb) {
                return "";
            } else {
                return sb.toString();
            }
        }
    }


    /***md5加密***/
    public static String setMD5(String string) {
        MessageDigest md5;
        StringBuilder sb = new StringBuilder();
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes("UTF-8"));
            byte[] b = md5.digest();
            for (byte aB : b) {
                int temp = 0xFF & aB;
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

    public static Map<String, Object> getIceMap(Context context, Map<String, Object> paramsMap) {
        String sign = "";
        String ts = getTime();
        try {
            sign = getSign(ts);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("appID", DES.APP_ID);
        map.put("sign", sign);
        map.put("ts", ts);
        HashMap<String, Object> mapsum = new HashMap<String, Object>();
        mapsum.putAll(map);
        mapsum.putAll(paramsMap);
        return getNewSaftyMap(context, mapsum);
    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getTime() {
        Long difference = SharedPreferencesUtils.getInstance().getLongData(SpConstants.UserModel.DIFFERENCE, -1l);
        return String.valueOf(difference);
    }

    /**
     * @return
     * @throws Exception
     */
    private static String getSign(String lastTime)
            throws Exception {
        return MD5.getMd5Value(DES.APP_ID + lastTime + DES.TOKEN + "false").toLowerCase();
    }

    /***4.0新接口的安全加密以后的请求参数Map**/
    public static Map<String, Object> getNewSaftyMap(Context context, Map<String, Object> paramsMap) {
        paramsMap.put("nonce_str", getRandomNonceStr());
        paramsMap.put("device_uuid", TokenUtils.getUUID(BaseApplication.getContext()));
        paramsMap.put("native_type", 1);
        paramsMap.put("version", VersionUtils.getVersionName(context));
        String buff = "";
        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(paramsMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {

                @Override
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return (o1.getKey()).toString().compareTo((o2.getKey()).toString());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds) {
                if (null != item && !TextUtils.isEmpty(item.getValue().toString())) {
                    String key = item.getKey();
                    String val = item.getValue().toString();
                    val = URLEncoder.encode(val, "utf-8");
                    val = val.replace(" ", "%20");
                    val = val.replace("*", "%2A");
                    val = val.replace("+", "%2B");
                    buf.append(key + "=" + val);
                    buf.append("&");
                }
            }
            buff = setMD5(buf.toString() + "secret=" + Contants.APP.secertKey).toUpperCase();
            paramsMap.put("signature", buff);
        } catch (Exception e) {
            return paramsMap;
        }
        return paramsMap;
    }


    public static Map<String, String> getStringMap(Map<String, Object> paramsMap) {
        Iterator<String> it = paramsMap.keySet().iterator();
        Map<String, String> stringMap = new HashMap<>();
        while (it.hasNext()) {
            String key = it.next();
            String value = String.valueOf(paramsMap.get(key));
            stringMap.put(key, value);
        }
        return stringMap;
    }

    public static Map<String, Object> getTrimMap(Map<String, Object> paramsMap) {
        Map<String, Object> trimParamMap = new HashMap<>();
        if (null != paramsMap) {
            Iterator<String> it = paramsMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = String.valueOf(paramsMap.get(key)).trim();
                trimParamMap.put(key, value);
            }
        }
        return trimParamMap;
    }

    /***4.0生成8位随机数算法*/
    private static String getRandomNonceStr() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXZY";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
