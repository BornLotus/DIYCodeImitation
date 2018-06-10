package com.bornlotus.diycodeimitation.activity.ui.base;

import android.view.View;

import com.bornlotus.diycodeimitation.activity.api.event.BaseEvent;

import java.util.List;

/**
 * Created by BornLotus on 2018/3/7.
 */

public class RefreshRecyclerFragment<T, Event extends BaseEvent<List<T>>> extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {

    }

    @Override
    protected void initData() {

    }
}
