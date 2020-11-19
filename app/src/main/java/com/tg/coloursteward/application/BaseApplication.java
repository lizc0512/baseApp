package com.tg.coloursteward.application;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tg.coloursteward.utils.SSLContextUtil;
import com.mob.MobSDK;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;

import javax.net.ssl.SSLContext;

import cn.jpush.android.api.JPushInterface;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.application
 * @class describe
 * @anthor ${lizc} QQ:510906433
 * @time 2019/1/7 15:17
 * @change
 * @chang time
 * @class describe
 */
public class BaseApplication extends MultiDexApplication {
    private static Context context;
    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        context = getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        InitializationConfig config = InitializationConfig.newBuilder(getApplicationContext())
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(20 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(20 * 1000)
                .networkExecutor(new URLConnectionNetworkExecutor())
                .sslSocketFactory(sslContext.getSocketFactory()) // 全局SSLSocketFactory。
                .retry(1)
                .build();
        NoHttp.initialize(config);
        JPushInterface.init(getApplicationContext());
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        MobSDK.init(this);
    }

    public static BaseApplication getInstance(){
        if(instance==null){
            instance=new BaseApplication();
        }
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base); //解决差分包的问题
    }
}
