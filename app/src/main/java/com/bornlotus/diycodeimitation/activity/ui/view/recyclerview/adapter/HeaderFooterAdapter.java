package com.bornlotus.diycodeimitation.activity.ui.view.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BornLotus on 2018/3/6.
 */

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
        implements TypePool {

    private List<Object> mItems = new ArrayList<>();
    private MultiTypePool mTypePool;

    private boolean hasHeader = false;
    private boolean hasFooter = false;

    public HeaderFooterAdapter() {
        mTypePool = new MultiTypePool();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewProvider provider = getProviderByIndex(viewType);
        return provider.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        assert mItems != null;
        Object item = mItems.get(position);
        BaseViewProvider provider = getProviderByClass(item.getClass());
        provider.onBindView(holder,item);
    }

    @Override
    public int getItemCount() {
        assert mItems != null;
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        assert mItems != null;
        Object item = mItems.get(position);
        return mTypePool.indexOf(item.getClass());
    }

    @Override
    public void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider) {
        mTypePool.register(clazz, provider);
    }

    @Override
    public int indexOf(@NonNull Class<?> clazz) {
        return mTypePool.indexOf(clazz);
    }

    @Override
    public List<BaseViewProvider> getProviders() {
        return mTypePool.getProviders();
    }

    @Override
    public BaseViewProvider getProviderByIndex(int index) {
        return mTypePool.getProviderByIndex(index);
    }

    @Override
    public <T extends BaseViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return mTypePool.getProviderByClass(clazz);
    }

    public void registerHeader(@NonNull Object object, @NonNull BaseViewProvider provider) {
        if (hasHeader) return;
        mTypePool.register(object.getClass(), provider);
        mItems.add(0, object);
        hasHeader = true;
        notifyDataSetChanged();
    }

    public void unRegisterHeader() {
        if (!hasHeader) return;
        mItems.remove(0);
        hasHeader = false;
        notifyDataSetChanged();
    }

}
