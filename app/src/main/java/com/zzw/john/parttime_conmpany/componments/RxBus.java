package com.zzw.john.parttime_conmpany.componments;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ZheWei on 2016/9/13.
 * 通过RxJava实现的 EventBus 的功能 RxBus
 */
public class RxBus {
    private static volatile RxBus mInstance;

    private SerializedSubject<Object, Object> mSubject;

    private HashMap<String, CompositeSubscription> mSubscriptionHashMap;

    private RxBus() {
        mSubject = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getmInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object event) {
        mSubject.onNext(event);
    }

    /**
     * 返回指定类型的 Observable实例
     */
    public <T> Observable<T> toObservable(Class<T> type) {
        return mSubject.ofType(type);
    }

    /**
     * 是否已经有了观察者在订阅
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * 一个默认的订阅方式--->不进行转换 observable 了
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error) {
        return toObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    /**
     * 保存订阅之后的 subscription
     */
    public void addSubscription(Object o, Subscription subscription) {
        if (mSubscriptionHashMap == null) {
            mSubscriptionHashMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionHashMap.get(key) != null) {
            mSubscriptionHashMap.get(key).add(subscription);
        } else {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            mSubscriptionHashMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     */
    public void unSubscription(Object o) {
        if (mSubscriptionHashMap == null) {
            return;
        }
        String key = o.getClass().getName();
        if (!mSubscriptionHashMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionHashMap.get(key) != null) {
            CompositeSubscription compositeSubscription = mSubscriptionHashMap.get(key);
            if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
                compositeSubscription.unsubscribe();
            }
        }
        mSubscriptionHashMap.remove(key);

    }
}
