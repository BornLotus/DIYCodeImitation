package com.bornlotus.diycodeimitation.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.adapter.NewsAdapter;

import java.util.List;

/**
 * Created by BornLotus on 2017/11/30.
 */

public class NewsFragment extends Fragment {

    View mRootView;
    private RecyclerView newsRecycler;
    private List<String> mData;
    private NewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null)
            mRootView = inflater.inflate(R.layout.news_fragment,container,false);
        newsRecycler = mRootView.findViewById(R.id.newsRecycler);
        mAdapter = new NewsAdapter(getActivity(), mData);
        newsRecycler.setAdapter(mAdapter);


        return mRootView;
    }


    public void setData(List<String> data){
        this.mData = data;
    }


}
