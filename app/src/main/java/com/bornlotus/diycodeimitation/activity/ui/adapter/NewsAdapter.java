package com.bornlotus.diycodeimitation.activity.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by BornLotus on 2017/12/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<String> mDatas;
    private Context mContext;
    public NewsAdapter(Context context,List<String> data){
        this.mContext = context;
        this.mDatas = data;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        public NewsHolder(View itemView) {
            super(itemView);
        }
    }

}
