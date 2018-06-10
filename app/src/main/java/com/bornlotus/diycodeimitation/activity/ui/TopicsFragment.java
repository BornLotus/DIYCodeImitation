package com.bornlotus.diycodeimitation.activity.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.api.module.topic.Topic;
import com.bornlotus.diycodeimitation.activity.ui.adapter.TopicAdapter;
import com.bornlotus.diycodeimitation.activity.ui.base.BaseFragment;
import com.bornlotus.diycodeimitation.activity.ui.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BornLotus on 2017/11/30.
 */

public class TopicsFragment extends BaseFragment {

    private static final String TAG = "TopicsFragment";
    private View mRootView;
    private RecyclerView mTopicRecycler;
    private List<Topic> mTopics;
    private TopicAdapter mAdapter;

    public TopicsFragment(){}

    @Override
    protected int getLayoutId() {
        return R.layout.topics_fragment;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        mTopicRecycler = holder.get(R.id.topicsRecycler);
        mTopicRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TopicAdapter(getContext(), mTopics);
        mTopicRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mTopics = new ArrayList<>();
    }

    public void refreshData(List<Topic> topics) {
        if (topics == null || topics.size() == 0) return;
//        if (mTopics == null) mTopics = new ArrayList<>();
        //手动去重
        mTopics.removeAll(topics);

        mTopics.addAll(0, topics);
        mAdapter.notifyDataSetChanged();
    }

    public void notifyDataChanged(List<Topic> topics) {
        if (topics == null || topics.size() == 0) return;
//        if (mTopics == null) mTopics = new ArrayList<>();
        mTopics.addAll(topics);
        mAdapter.notifyDataSetChanged();
    }

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.topics_fragment,container,false);
        mRootView.findViewById(R.id.get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpServiceManager.newInstance().getTopicList(null, 1, 1, 10,
                        new Observer<List<Topic>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("current thread is main thread? " +
                                        (Thread.currentThread() == Looper.getMainLooper().getThread()));
                            }

                            @Override
                            public void onNext(List<Topic> topics) {
                                for (Topic topic : topics) {
                                    System.out.println("topic = " + topic.toString());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        return mRootView;
    }*/
}
