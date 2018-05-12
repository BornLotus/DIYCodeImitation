package com.bornlotus.diycodeimitation.activity.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.api.http.HttpServiceManager;
import com.bornlotus.diycodeimitation.activity.api.module.topic.Topic;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by BornLotus on 2017/11/30.
 */

public class TopicsFragment extends Fragment {

    private static final String TAG = "TopicsFragment";
    private View mRootView;


    @Nullable
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

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        return mRootView;
    }
}
