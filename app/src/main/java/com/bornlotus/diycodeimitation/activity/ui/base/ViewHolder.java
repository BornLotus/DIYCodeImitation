package com.bornlotus.diycodeimitation.activity.ui.base;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by BornLotus on 2018/3/7.
 */

public class ViewHolder {

    private final String TAG = ViewHolder.class.getSimpleName();

    private SparseArray<View> mViews;
    private View mRootView;

    public ViewHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<>();
        mRootView = inflater.inflate(layoutId, parent, false);
    }

    /**
     * 通过View的id来获取子View
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T get(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = mRootView.findViewById(resId);
            mViews.put(resId, view);
        }
        if (view == null) {
            Log.e(TAG, "View == null");
        }
        return (T) view;
    }

    /**
     * 获取根布局View
     * @return
     */
    public View getRootView() {
        return mRootView;
    }

    public boolean setText(CharSequence text, int resId) {
        try {
            TextView textView = get(resId);
            textView.setText(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean setText(int resId, CharSequence text) {
        return setText(text, resId);
    }

    public void loadImage(Context context, String url, int resId) {
        ImageView imageView = get(resId);
        String url2 = url;
        if (url.contains("diycode")) {
            url2 = url.replace("large_avatar", "avatar");
        }
        Glide.with(context).load(url2).into(imageView);
    }

    /**
     * 设置监听器
     * @param listener
     * @param ids
     */
    public void setOnclickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) return;
        for (int id:ids) {
            get(id).setOnClickListener(listener);
        }
    }

}
