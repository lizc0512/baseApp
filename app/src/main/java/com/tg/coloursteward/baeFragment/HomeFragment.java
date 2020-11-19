package com.tg.coloursteward.baeFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.tg.coloursteward.adapter.HomeAdapter;
import com.tg.user.activity.LoginActivity;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.baseActivity
 * @class 首页页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private Context mContext;
    private ImageView iv_base_back;
    private ImageView iv_base_location;
    private ImageView iv_home_scan;
    private ImageView iv_home_notice;
    private TextView tv_base_location;
    private RecyclerView recyclerview;
    private HomeAdapter homeAdapter;
    private View view_banner;
    private View view_function;
    private UltraViewPager vp_home_banner;
    private RecyclerView rv_home_function;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = this.getActivity();
        initView();
        return view;
    }

    private void initView() {
        TextView tv_login = view.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
        recyclerview = view.findViewById(R.id.recyclerview);
        iv_base_back = view.findViewById(R.id.iv_base_back);
        iv_base_location = view.findViewById(R.id.iv_base_location);
        iv_home_scan = view.findViewById(R.id.iv_home_scan);
        iv_home_notice = view.findViewById(R.id.iv_home_notice);
        tv_base_location = view.findViewById(R.id.tv_base_location);
        iv_base_back.setVisibility(View.GONE);
        iv_base_location.setVisibility(View.VISIBLE);
        tv_base_location.setVisibility(View.VISIBLE);
        iv_home_notice.setVisibility(View.VISIBLE);
        iv_home_scan.setVisibility(View.VISIBLE);
        iv_home_scan.setOnClickListener(this);
        iv_home_notice.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        list.add("lizhi");
        list.add("chenshujian");
        if (null == homeAdapter) {
            homeAdapter = new HomeAdapter(R.layout.item_home_rv, list);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(homeAdapter);
        initBannerView();
        initFunctionView();
    }

    private void initBannerView() {
        if (null == view_banner) {
            view_banner = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner, null);
            vp_home_banner = view_banner.findViewById(R.id.vp_home_banner);
            homeAdapter.addHeaderView(view_banner);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view_banner = null;
        if (homeAdapter.getEmptyView() == null) {
            ((FrameLayout) homeAdapter.getEmptyView()).removeAllViews();
        }
    }

    private void initFunctionView() {
        view_function = LayoutInflater.from(mContext).inflate(R.layout.item_home_function, null);
        view_function.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rv_home_function = view_banner.findViewById(R.id.rv_home_function);
        homeAdapter.addHeaderView(view_function);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = this.getActivity();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_scan:
                break;
            case R.id.iv_home_notice:

                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
