package com.tg.coloursteward.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.tg.coloursteward.baseActivity.WebViewActivity;
import com.tg.user.activity.LoginActivity;


/**
 * @name ${lizc}
 * @class name：com.colourlife.safelise.utils
 * @class 通用跳转工具类
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/09 13:57
 * @chang time
 */
public class LinkParseUtil {
    public static void parse(Activity context, String link, String title) {
        if (!TextUtils.isEmpty(link)) {
            if (link.startsWith("http://") || link.startsWith("https://")) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(WebViewActivity.web_url, link);
                intent.putExtra(WebViewActivity.web_title, title);
                context.startActivity(intent);
            } else {
                if (link.startsWith("colourlife://proto") && link.length() > 24) {//colourlife://proto?type=XXX
                    String name = link.substring(24, link.length());
                    Intent it;
                    if (name.equals("login")) {//登录页面
                        it = new Intent(context, LoginActivity.class);
                        context.startActivity(it);
                    } else {
                        return;
                    }
                }
            }
        }
    }
}

