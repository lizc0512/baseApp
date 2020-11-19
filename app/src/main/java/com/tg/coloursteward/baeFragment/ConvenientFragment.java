package com.tg.coloursteward.baeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tg.coloursteward.R;
import com.tg.coloursteward.baseModel.HomeModel;

/**
 * @name ${lizc}
 * @class name：com.colourlife.safelife.baseFragment
 * @class 便民页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class ConvenientFragment extends Fragment {
    private static final String TAG = ConvenientFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnConveninetListener mListener;
    private HomeModel homeModel;

    public ConvenientFragment() {
    }

    public static ConvenientFragment newInstance(String param1, String param2) {
        ConvenientFragment fragment = new ConvenientFragment();
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
        View view = inflater.inflate(R.layout.fragment_convenient, container, false);
        initView();
        initData();
        initEvents();
        return view;
    }

    private void initView() {
        homeModel = new HomeModel(getActivity());
    }

    private void initData() {

    }

    private void initEvents() {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSecondInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConveninetListener) {
            mListener = (OnConveninetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnConveninetListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnConveninetListener {
        void onSecondInteraction(Uri uri);
    }
}
