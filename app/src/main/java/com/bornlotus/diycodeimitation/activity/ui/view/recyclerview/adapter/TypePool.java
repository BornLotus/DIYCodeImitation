package com.bornlotus.diycodeimitation.activity.ui.view.recyclerview.adapter;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by BornLotus on 2018/3/6.
 */

public interface TypePool {

    void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider);

    int indexOf(@NonNull Class<?> clazz);

    List<BaseViewProvider> getProviders();

    BaseViewProvider getProviderByIndex(int index);

    <T extends BaseViewProvider> T getProviderByClass(@NonNull final Class<?> clazz);

}
