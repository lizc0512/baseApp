package com.tg.coloursteward.utils;


import android.text.TextUtils;
import android.util.SparseArray;

import com.tg.user.entity.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class HttpTools {
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_MESSAGE = "message";

    /**
     * 获取Content信息(String)
     *
     * @param jsonString
     * @return
     */
    public static String getContentString(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        if (jsonObj.isNull(FIELD_CONTENT)) {
            return null;
        }
        return jsonObj.optString(FIELD_CONTENT);
    }

    /**
     * 获取Content详细数据调用方法
     *
     * @param jsonString
     * @return
     */
    public static ResponseData getResponseContentObject(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            return new ResponseData(null);
        }
        return parseUserInfoJsonString(jsonString);
    }

    public static ResponseData parseUserInfoJsonString(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return new ResponseData(null);
        }
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseData(null);
        }
        SparseArray<HashMap<String, Object>> sparseArray = new SparseArray<HashMap<String, Object>>();
        sparseArray.put(0, getMap(jsonObj));
        return new ResponseData(sparseArray);
    }

    public static HashMap<String, Object> getMap(JSONObject jsonObj) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (jsonObj == null) {
            return map;
        }
        Iterator<String> keys = jsonObj.keys();
        if (keys == null) {
            return map;
        }
        String key;
        while (keys.hasNext()) {
            key = keys.next();
            if (jsonObj.isNull(key)) {
                map.put(key, "");
            } else {
                map.put(key, jsonObj.opt(key));
            }
        }
        return map;
    }

    /**
     * 获取Content信息(JSONObject)
     *
     * @param jsonString
     * @return
     */
    public static JSONObject getContentJSONObject(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        if (jsonObj.isNull(FIELD_CONTENT)) {
            return null;
        } else {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getContentString(jsonString));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }
    /**
     * 获取Message信息
     *
     * @param jsonString
     * @return
     */
    public static String getMessageString(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        if (jsonObj.isNull(FIELD_MESSAGE)) {
            return null;
        }
        return jsonObj.optString(FIELD_MESSAGE, null);
    }
}