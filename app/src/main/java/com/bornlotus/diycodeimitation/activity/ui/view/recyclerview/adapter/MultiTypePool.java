package com.bornlotus.diycodeimitation.activity.ui.view.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BornLotus on 2018/3/6.
 */

public class MultiTypePool implements TypePool {

    private final String TAG = MultiTypePool.class.getSimpleName();
    private final List<Class<?>> mContents;
    private final List<BaseViewProvider> mProviders;

    public MultiTypePool() {
        mContents = new ArrayList<>();
        mProviders = new ArrayList<>();
    }

    /**
     * 将数据类型和provider关联起来
     * @param clazz
     * @param provider
     */
    @Override
    public void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider) {
        if (!mContents.contains(clazz)) {
            mContents.add(clazz);
            mProviders.add(provider);
        } else {
            int index = mContents.indexOf(clazz);
            mProviders.set(index, provider);
            Log.w(TAG, "You have register the " + provider.getClass().getSimpleName()
            + " type. " + "It will override the original binder.");
        }
    }

    @Override
    public int indexOf(@NonNull Class<?> clazz) throws ProviderNotFoundException{
        int index = mContents.indexOf(clazz);
        if (index >= 0) {
            return index;
        }
        for (int i = 0; i < mContents.size(); i++) {
            if (mContents.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        throw new ProviderNotFoundException(clazz);
    }

    @Override
    public List<BaseViewProvider> getProviders() {
        return mProviders;
    }

    @Override
    public BaseViewProvider getProviderByIndex(int index) {
        return mProviders.get(index);
    }

    @Override
    public <T extends BaseViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return (T) getProviderByIndex(mContents.indexOf(clazz));
    }

    public List<Class<?>> getContents() {
        return mContents;
    }

    public class ProviderNotFoundException extends RuntimeException {

        public ProviderNotFoundException(@NonNull Class<?> clazz) {
            super("Do you have registered the provider for {className}.class in the adapter/pool?"
                    .replace("{className}", clazz.getSimpleName()));
        }

    }

}
