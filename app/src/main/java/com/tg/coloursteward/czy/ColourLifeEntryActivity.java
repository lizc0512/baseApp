package com.tg.coloursteward.czy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.tg.coloursteward.R;
import com.tg.coloursteward.baseActivity.BaseActivity;
import com.tg.user.activity.LoginActivity;

import static com.tg.user.activity.LoginActivity.CZY_CODE;

public class ColourLifeEntryActivity extends BaseActivity {
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_colour_life_entry;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (null != intent) {
            String data = intent.getStringExtra("code");
            if (!TextUtils.isEmpty(data)) {
                code = data.substring(5, data.length());
            }
            if (!TextUtils.isEmpty(code)) {
                Intent it = new Intent(ColourLifeEntryActivity.this, LoginActivity.class);
                it.putExtra(CZY_CODE, code);
                startActivity(it);
            }

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvents() {

    }

}
