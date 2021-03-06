package com.tg.coloursteward.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tg.coloursteward.R;

import java.io.File;

/**
 * @name ${lizc}
 * @class name：com.colourlife.qfqz.utils
 * @class describe
 * @anthor ${lizc} QQ:510906433
 * @time 2019/1/7 19:46
 * @change
 * @chang time
 * @class describe
 */
public class GlideUtils {

    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        try {
            if (null != mContext) {
                Glide.with(mContext).load(path).apply((new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL)).into(mImageView);
            }
        } catch (Exception var4) {
            ;
        }

    }

    public static void loadHeadPhoto(Context mContext, String path, ImageView mImageView) {
        try {
            if (null != mContext) {
                Glide.with(mContext).load(path).apply((new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)).into(mImageView);
            }
        } catch (Exception var4) {
            ;
        }

    }

    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        try {
            if (mContext != null) {
                Glide.with(mContext).asBitmap().load(new File(path)).apply((new RequestOptions()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(width, height).centerCrop().format(DecodeFormat.PREFER_RGB_565)).thumbnail(0.5F).into(mImageView);
            }
        } catch (Exception var6) {
            ;
        }

    }

    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        try {
            if (mContext != null) {
                Glide.with(mContext).load(path).apply((new RequestOptions()).placeholder(lodingImage).error(errorImageView)).into(mImageView);
            }
        } catch (Exception var6) {
            ;
        }

    }

    public static void clearImagView(Context mContext, ImageView mImageView) {
        try {
            if (mContext != null) {
                Glide.with(mContext).clear(mImageView);
            }
        } catch (Exception var3) {
            ;
        }

    }
}
