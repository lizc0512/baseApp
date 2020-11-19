package com.tg.coloursteward.baeFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.baseActivity
 * @class 商城页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class ShoppingFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnShoppingInteractionListener mListener;
    private Context mContext;
    private ImageView iv_base_back;
    private TextView tv_base_title;
    private AgentWeb agentWeb;
    private String webTitle;
    private String webUrl;
    private View view;

    public ShoppingFragment() {
    }

    public static ShoppingFragment newInstance(String param1, String param2) {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        mContext = this.getActivity();
        initView();
        initData();
        return view;
    }

    private void initData() {
        AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) view.findViewById(R.id.ll_webview_shopping), new LinearLayout.LayoutParams(-1, -1))
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
            if (!TextUtils.isEmpty(title)) {
                webTitle = title;
                if (webTitle.length() > 10) {
                    webTitle = title.substring(0, 10) + "...";
                }
            }
            if (!TextUtils.isEmpty(webTitle)) {
                tv_base_title.setText(webTitle);
            }
        }
    };

    private void initView() {
        tv_base_title = view.findViewById(R.id.tv_base_title);
        iv_base_back = view.findViewById(R.id.iv_base_back);
        iv_base_back.setVisibility(View.GONE);
        webUrl = "https://m.jd.com/";
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFourInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = this.getActivity();
        if (context instanceof OnShoppingInteractionListener) {
            mListener = (OnShoppingInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnShoppingInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public interface OnShoppingInteractionListener {
        void onFourInteraction(Uri uri);
    }
}
