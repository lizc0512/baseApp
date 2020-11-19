package com.tg.coloursteward.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tg.coloursteward.R;

import java.util.List;

/**
 * @name ${lizc}
 * @class name：com.colourlife.safelife.adapter
 * @class 首页适配器
 * @anthor ${lizc} QQ:510906433
 * @time 2019/1/14 19:32
 * @change
 * @chang time
 * @class describe
 */
public class HomeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_home_test, item);

    }
}
