package com.bornlotus.diycodeimitation.activity.api.http;

import com.bornlotus.diycodeimitation.activity.api.utils.Constant;

import io.reactivex.Observable;
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

    public <T> void getTopicList(String type, int id, int offset, int limit,Observer<T> observer){
        Observable<T> topicList = mService.getTopicList(type, id, offset, limit);
        topicList.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
