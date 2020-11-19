package com.tg.coloursteward.baseActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.tg.coloursteward.utils.SharedPreferencesUtils;

public abstract class BaseActivity extends AppCompatActivity {
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    protected SharedPreferencesUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intiLayout());
        spUtils = SharedPreferencesUtils.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initView();//初始化控件
        initData();//设置数据
        initEvents();//控件监听

    }

    public abstract int intiLayout();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvents();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {//还原字体大小
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }
}
