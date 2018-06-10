package com.bornlotus.diycodeimitation.activity.ui.view.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 *          ItemView的管理者
 * Created by BornLotus on 2018/3/6.
 */

public abstract class BaseViewProvider<T> {

    private LayoutInflater mInflater;
    private int mLayoutId;
    private Context mContext;

    public BaseViewProvider(@NonNull Context context, @NonNull @LayoutRes int layout_id) {
        mInflater = LayoutInflater.from(context);
        mLayoutId = layout_id;
        mContext = context;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        onViewHolderIsCreated(viewHolder);
        return viewHolder;
    }

    /**
     * 当ViewHolder创建后调用
     * @param holder
     */
    public void onViewHolderIsCreated(RecyclerViewHolder holder) {
    }

    /**
     * 在绑定数据是调用，需用户自己处理
     * @param holder
     * @param bean
     */
    public abstract void onBindView(RecyclerViewHolder holder, T bean);

}
