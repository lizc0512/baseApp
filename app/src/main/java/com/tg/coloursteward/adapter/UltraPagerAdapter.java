package com.tg.coloursteward.adapter;

/**
 * @name ${yuansk}
 * @class name：cn.net.cyberway.home.adapter
 * @class describe
 * @anthor ${ysk} QQ:827194927
 * @time 2018/8/17 14:56
 * @change
 * @chang time
 * @class describe
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tg.coloursteward.R;
import com.tg.coloursteward.entity.HomeFuncEntity;

import java.util.ArrayList;


/**
 * 首页底部活动banner
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private Context mContext;
    private ArrayList<HomeFuncEntity.ContentBean> bannerDataBeanList;

    public UltraPagerAdapter(Context mContext, boolean isMultiScr, ArrayList<HomeFuncEntity.ContentBean> bannerDataBeanList) {
        this.isMultiScr = isMultiScr;
        this.mContext = mContext;
        this.bannerDataBeanList = bannerDataBeanList;
    }


    @Override
    public int getCount() {
        return bannerDataBeanList == null ? 0 : bannerDataBeanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final HomeFuncEntity.ContentBean contentBean = bannerDataBeanList.get(position);
        View bannerView = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_guesslike_layout_only, null);
//        ImageView iv_activity = bannerView.findViewById(R.id.iv_activity);
//        GlideImageLoader.loadImageDefaultDisplay(mContext, contentBean.getImg(), iv_activity, R.drawable.icon_style_one, R.drawable.icon_style_one);
//        bannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LinkParseUtil.parse(mContext, contentBean.getRedirect_uri(), contentBean.getName());
//            }
//        });
        container.addView(bannerView);
        return bannerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
