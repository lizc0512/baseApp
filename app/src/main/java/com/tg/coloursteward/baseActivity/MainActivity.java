package com.tg.coloursteward.baseActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.tg.coloursteward.view.MyViewPager;
import com.tg.coloursteward.baeFragment.ConvenientFragment;
import com.tg.coloursteward.baeFragment.HomeFragment;
import com.tg.coloursteward.baeFragment.MineFragment;
import com.tg.coloursteward.baeFragment.ShoppingFragment;
import com.tg.coloursteward.constants.ConstantHelper;
import com.tg.coloursteward.utils.LinkParseUtil;
import com.tg.coloursteward.utils.VersionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.baseActivity
 * @class APP主页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, HomeFragment.OnFragmentInteractionListener, ConvenientFragment.OnConveninetListener,
        ShoppingFragment.OnShoppingInteractionListener, MineFragment.OnMineInteractionListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    public static final String JUMPOTHERURL = "jumpotherurl";
    public static final String KEY_SKIN_CODE = "skin_code";
    public static final String KEY_NEDD_FRESH = "need_fresh";
    public static final String FROM_LOGIN = "from_login";
    private LinearLayout main_tool_home;
    private LinearLayout main_tool_convenient;
    private LinearLayout main_tool_shopping;
    private LinearLayout main_tool_mine;
    private List<Fragment> mFragments = new ArrayList<>();
    private TabFragmentPagerAdapter mAdapter;
    private FragmentManager fragmentManager;
    private MyViewPager viewPager;
    private TextView tv_home;
    private TextView tv_convenient;
    private TextView tv_shopping;
    private TextView tv_mine;
    private String url_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils.saveIntData(ConstantHelper.sharepreName.VERSION_CODE, VersionUtils.getVersionCode(MainActivity.this));//保存版本code号
        initView();
        initData();
        initEvents();
        CheckPermission();
        setAlias();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }


    private void setAlias() {

    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_CONTACTS,
                            android.Manifest.permission.READ_PHONE_STATE},
                    Activity.DEFAULT_KEYS_SEARCH_LOCAL);
        }
    }

    @Override
    public void initEvents() {
        main_tool_home.setOnClickListener(this);
        main_tool_convenient.setOnClickListener(this);
        main_tool_shopping.setOnClickListener(this);
        main_tool_mine.setOnClickListener(this);
        Intent intent = getIntent();
        if (null != intent) {
            url_jump = intent.getStringExtra(JUMPOTHERURL);
        }
        if (!TextUtils.isEmpty(url_jump)) {
            LinkParseUtil.parse(MainActivity.this, url_jump, "");
        }

    }

    @Override
    public void initView() {
        main_tool_home = findViewById(R.id.main_tool_home);
        main_tool_convenient = findViewById(R.id.main_tool_convenient);
        main_tool_shopping = findViewById(R.id.main_tool_shopping);
        main_tool_mine = findViewById(R.id.main_tool_mine);
        viewPager = findViewById(R.id.view_pager);
        tv_home = findViewById(R.id.tv_home);
        tv_convenient = findViewById(R.id.tv_convenient);
        tv_shopping = findViewById(R.id.tv_shopping);
        tv_mine = findViewById(R.id.tv_mine);
    }

    @Override
    public void initData() {
        mFragments.add(new HomeFragment());
        mFragments.add(new ConvenientFragment());
        mFragments.add(new ShoppingFragment());
        mFragments.add(new MineFragment());
        fragmentManager = getSupportFragmentManager();
        mAdapter = new TabFragmentPagerAdapter(fragmentManager);
        viewPager.setAdapter(mAdapter);
        viewPager.setScanScroll(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
        tv_home.setSelected(true);
        if (spUtils.getBooleanData(ConstantHelper.sharepreName.ISCHECK_UPDATE, false)) {
            //第一次启动APP不检查更新，第二次才检测更新
        }
        spUtils.saveBooleanData(ConstantHelper.sharepreName.ISCHECK_UPDATE, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tool_home:
                viewPager.setCurrentItem(0);
                tv_home.setSelected(true);
                tv_convenient.setSelected(false);
                tv_shopping.setSelected(false);
                tv_mine.setSelected(false);
                break;
            case R.id.main_tool_convenient:
                viewPager.setCurrentItem(1);
                tv_home.setSelected(false);
                tv_convenient.setSelected(true);
                tv_shopping.setSelected(false);
                tv_mine.setSelected(false);
                break;
            case R.id.main_tool_shopping:
                viewPager.setCurrentItem(2);
                tv_home.setSelected(false);
                tv_convenient.setSelected(false);
                tv_shopping.setSelected(true);
                tv_mine.setSelected(false);
                break;
            case R.id.main_tool_mine:
                viewPager.setCurrentItem(3);
                tv_home.setSelected(false);
                tv_convenient.setSelected(false);
                tv_shopping.setSelected(false);
                tv_mine.setSelected(true);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSecondInteraction(Uri uri) {

    }

    @Override
    public void onFourInteraction(Uri uri) {

    }

    @Override
    public void onFiveInteraction(Uri uri) {

    }

    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        private TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    private void reSetColor() {

    }

}
