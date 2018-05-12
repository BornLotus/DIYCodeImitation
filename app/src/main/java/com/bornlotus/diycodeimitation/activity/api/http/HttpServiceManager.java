package com.bornlotus.diycodeimitation.activity.api.http;

import android.support.annotation.NonNull;

import com.bornlotus.diycodeimitation.activity.api.module.topic.State;
import com.bornlotus.diycodeimitation.activity.api.module.topic.Topic;
import com.bornlotus.diycodeimitation.activity.api.module.topic.TopicContent;
import com.bornlotus.diycodeimitation.activity.api.module.topic.TopicReply;
import com.bornlotus.diycodeimitation.activity.api.utils.Constant;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BornLotus on 2017/12/3.
 */

public class HttpServiceManager {

    private Retrofit mRetrofit;
    private static HttpServiceManager mManager;
    private HttpService mService;

    private HttpServiceManager(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = mRetrofit.create(HttpService.class);
    }

    public static HttpServiceManager newInstance(){
        if (mManager == null){
            synchronized (HttpServiceManager.class){
                if (mManager == null){
                    mManager = new HttpServiceManager();
                }
            }
        }
        return mManager;
    }

    public void getTopicList(String type, int id, int offset, int limit, @NonNull Observer<List<Topic>> observer){
        mService.getTopicList(type, id, offset, limit)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createTopic(String title, String body, int node_id, @NonNull Observer<TopicContent> observer) {
        mService.createTopic(title, body, node_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTopic(int id, Observer<TopicContent> observer) {
        mService.getTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void updateTopic(int id, String title, String body, int node_id, Observer<TopicContent> observer) {
        mService.updateTopic(id, title, body, node_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void deleteTopic(int id, Observer<State> observer) {
        mService.deleteTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void collectTopic(int id, Observer<State> observer) {
        mService.collectTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void unCollectTopic(int id, Observer<State> observer) {
        mService.unCollectTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void watchTopic(int id, Observer<State> observer) {
        mService.watchTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void unWatchTopic(int id, Observer<State> observer) {
        mService.unWatchTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTopicRepliesList(int id, int offset, int limit, Observer<List<TopicReply>> observer) {
        mService.getTopicRepliesList(id, offset, limit)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createTopicReply(int id, String body, Observer<TopicReply> observer) {
        mService.createTopicReply(id, body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTopicReply(int id, Observer<TopicReply> observer) {
        mService.getTopicReply(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void updateTopicReply(int id, String body, Observer<TopicReply> observer) {
        mService.updateTopicReply(id, body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void deleteTopicReply(int id, Observer<State> observer) {
        mService.deleteTopicReply(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void banTopic(int id, Observer<State> observer) {
        mService.banTopic(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
