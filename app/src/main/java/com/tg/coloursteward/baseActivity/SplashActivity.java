package com.tg.coloursteward.baseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.tg.coloursteward.constants.SpConstants;
import com.tg.user.activity.LoginActivity;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.baseActivity
 * @class 欢迎页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/09 17:30
 * @chang time
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_splash_jump;
    private TimeCount timeCount = null;
    private int delayTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initEvents() {
        tv_splash_jump.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_splash_jump.setVisibility(View.VISIBLE);
        timeCount = new TimeCount(delayTime, 1000);
        timeCount.start();
    }

    @Override
    public void initView() {
        tv_splash_jump = findViewById(R.id.tv_splash_jump);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_splash_jump:
                cancelTime();
                redirectto("");
                break;
        }
    }

    private void cancelTime() {
        if (null != timeCount) {
            timeCount.cancel();
            timeCount = null;
        }
    }

    /**
     * 定义一个倒计时的内部类
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_splash_jump.setText(getResources().getString(R.string.tv_splash_jump) + "(" + 0 + "s)");
            redirectto("");
            cancelTime();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_splash_jump.setText(getResources().getString(R.string.tv_splash_jump) + "(" + millisUntilFinished / 1000 + "s)");
        }
    }

    /**
     * 如果第一次运行或者及版本更新就跳转到引导页，否则跳转到主界面
     */
    private void redirectto(String jumpUrl) {
        boolean islead=spUtils.getBooleanData(SpConstants.UserModel.isshowlead,false);
        if (!islead) {
            Intent intent = new Intent(this, LeadActivity.class);
            startActivity(intent);
            spUtils.saveBooleanData(SpConstants.UserModel.isshowlead,true);
            finish();
        } else {
            boolean islogin = spUtils.getBooleanData(SpConstants.UserModel.islogin, false);
            Intent intent = null;
            if (islogin) {
                intent = new Intent(this, MainActivity.class);
                intent.putExtra(MainActivity.JUMPOTHERURL, jumpUrl);
                startActivity(intent);
            } else {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }

    /**
     * 屏蔽返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
