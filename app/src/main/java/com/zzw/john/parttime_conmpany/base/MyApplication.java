package com.zzw.john.parttime_conmpany.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Created by ZheWei on 2016/9/13.
 */
public class MyApplication extends Application {
    //得到上下文
    private static Context context;
    //得到主线程
    private static Thread mainThread;
    //得到主线程的id
    private static long mainThreadId;
    //得到主线程的looper
    private static Looper looper;
    //得到handler
    private static Handler handler;



    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        context = getApplicationContext();
        mainThread = Thread.currentThread();        //主线程     //线程池
        mainThreadId = android.os.Process.myTid(); //myTid(线程id) Pid(进程id) Uid(用户的id)
        looper = getMainLooper(); //主线程的 looper
        handler = new Handler();  //得到handler (主线程)
        //bootstrap
        TypefaceProvider.registerDefaultIconSets();

    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }

    public static Looper getLooper() {
        return looper;
    }

    public static Handler getHandler() {
        return handler;
    }

}
