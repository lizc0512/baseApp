package com.tg.coloursteward.baeFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tg.coloursteward.R;
import com.tg.coloursteward.adapter.FragmentMineAdapter;
import com.tg.coloursteward.callBack.FragmentMineCallBack;
import com.tg.coloursteward.constants.SpConstants;
import com.tg.coloursteward.entity.FragmentMineEntity;
import com.tg.coloursteward.utils.GsonUtils;
import com.tg.coloursteward.utils.SharedPreferencesUtils;
import com.tg.coloursteward.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.baseActivity
 * @class 我的页面
 * @anthor ${lizc} QQ:510906433
 * @time 2019/01/06 13:57
 * @chang time
 */
public class MineFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnMineInteractionListener mListener;
    private View mView;
    private Activity mActivity;
    private AlertDialog dialog;
    private RecyclerView recyclerview;
    private List<FragmentMineEntity.ContentBean> list = new ArrayList<>();
    private List<FragmentMineEntity.ContentBean.DataBean> list_item = new ArrayList<>();
    private FragmentMineAdapter mineAdapter;
    private int openType;
    private String salary;
    //    private HomeService homeService;
    private RelativeLayout rl_mine_title;
    private TextView tv_mine_name;
    private TextView tv_mine_job;
    private CircleImageView iv_mine_head;

    public MineFragment() {
    }

    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        mView = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {

    }

    private void initData() {
        String json = SharedPreferencesUtils.getInstance().getStringData(SpConstants.storage.FRAGMENTMINE, "");
        if (!TextUtils.isEmpty(json)) {//有网络数据缓存
//            initDataAdapter(json);
        } else {
            String loaclCache = SpConstants.storage.fragmentminedata;
//            initDataAdapter(loaclCache);
        }
//        initGetData();
    }

    private void initDataAdapter(String json) {
        list.clear();
        list_item.clear();
        FragmentMineEntity fragmentMineEntity = new FragmentMineEntity();
        fragmentMineEntity = GsonUtils.gsonToBean(json, FragmentMineEntity.class);
        list.addAll(fragmentMineEntity.getContent());
        for (int i = 0; i < list.size(); i++) {
            list_item.addAll(list.get(i).getData());
        }
        if (null == mineAdapter) {
            mineAdapter = new FragmentMineAdapter(mActivity, list_item);
            recyclerview.setAdapter(mineAdapter);
        } else {
            mineAdapter.setData(list_item);
        }
        mineAdapter.setFragmentMineCallBack(new FragmentMineCallBack() {
            @Override
            public void getData(String result, int positon) {
//                String url = list_item.get(positon).getUrl();
//                String name = list_item.get(positon).getName();
//                if (url.contains("findPwd")) {
//                    openType = 1;
////                    find_pay_password();
//                    Cqb_PayUtil.getInstance(mActivity).createPay(getPublicParams(), getEnvironment());
//                } else if (name.contains("工资")) {
//                    openType = 2;
//                    salary = url;
//                    long salary_time;
//                    if (TextUtils.isEmpty(Tools.getStringValue(mActivity, Contants.storage.SALARY_TIME))) {
//                        salary_time = 0;
//                    } else {
//                        salary_time = Long.parseLong(Tools.getStringValue(mActivity, Contants.storage.SALARY_TIME));
//                    }
//                    long now_time = System.currentTimeMillis() / 1000;
//                    boolean isinput = Tools.getBooleanValue(mActivity, Contants.storage.SALARY_ISINPUT);
//                    if (isinput == true && now_time - salary_time >= 300) {//超过300秒
//                        find_pay_password();
//                    } else if (isinput == true) {
//                        LinkParseUtil.parse(mActivity, salary, "");
//                    } else {
//                        find_pay_password();
//                    }
//                } else if (name.contains("账号绑定")) {
//                    getAuth("绑定彩之云", url, "bdczy");
//                } else {
//                    LinkParseUtil.parse(mActivity, url, "");
//                }
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFiveInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMineInteractionListener) {
            mListener = (OnMineInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMineInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMineInteractionListener {
        void onFiveInteraction(Uri uri);
    }
}
