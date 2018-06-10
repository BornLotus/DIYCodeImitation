package com.bornlotus.diycodeimitation.activity.ui.view.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by BornLotus on 2018/3/6.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private View mRootView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mRootView = itemView;
    }

    private final SparseArray<View> mViews = new SparseArray<View>();

    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = mRootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) return;
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void setText(int id, String text) {
        TextView view = get(id);
        view.setText(text);
    }

}
