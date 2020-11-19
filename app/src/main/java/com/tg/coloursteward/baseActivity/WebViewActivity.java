package com.tg.coloursteward.baseActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.tg.coloursteward.utils.Utils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.application
 * @class describe
 * @anthor ${lizc} QQ:510906433
 * @time 2019/1/9 14:17
 * @change
 * @chang time
 * @class describe
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    public static String web_url = "webview_url";
    public static String web_title = "webview_title";
    private ImageView iv_base_close;
    private TextView tv_base_title;
    private AgentWeb agentWeb;
    private String webTitle;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
        iv_base_close = findViewById(R.id.iv_base_close);
        tv_base_title = findViewById(R.id.tv_base_title);
        iv_base_close.setVisibility(View.VISIBLE);
        tv_base_title.setVisibility(View.VISIBLE);
        if (null != getIntent()) {
            webTitle = getIntent().getStringExtra(web_title);
            webUrl = getIntent().getStringExtra(web_url);
        }
        webUrl = "https://www.baidu.com";
    }

    @Override
    public void initData() {
        AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) findViewById(R.id.ll_webview), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()// 使用默认进度条
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(webUrl);
    }

    @Override
    public void initEvents() {

    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (!TextUtils.isEmpty(title) && !Utils.isSpecialharacter(title)) {
                webTitle = title;
                if (webTitle.length() > 8) {
                    webTitle = title.substring(0, 8) + "...";
                }
            }
            if (!TextUtils.isEmpty(webTitle)) {
                tv_base_title.setText(webTitle);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        } else if (!agentWeb.back()) {
            WebViewActivity.this.finish();
        } else {
            agentWeb.back();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
