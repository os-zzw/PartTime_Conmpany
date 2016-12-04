package com.zzw.john.parttime_conmpany.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.zzw.john.parttime_conmpany.base.MyApplication;


/**
 * Created by ZheWei on 2016/9/13.
 * 界面常用工具类
 */
public class UIUtils {

    private static Toast toast;


    /**
     * 得到上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到Resources对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到 String.xml中的字符串
     */
    public static String getString(int id) {
        return getResources().getString(id);
    }

    /**
     * 得到 带有占位符 的String.xml中的字符串
     */
    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    /**
     * 得到 String.xml中的字符串数组
     */
    public static String[] getStringArr(int id) {
        return getResources().getStringArray(id);
    }

    /**
     * 得到Color.xml 中的颜色
     */
    public static int getColor(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    /**
     * 得到 mipmap/drawable下的图片
     */
    public static Drawable getPicture(int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    /**
     * show Toast
     */
    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 得到应用的包名
     */
    public static String getPackgeName() {
        return getContext().getPackageName();
    }

    /**
     * 安全的执行一个任务--->在主线程中执行
     */
    public static void postTaskSafely(Runnable task) {
        //当前线程的id
        int myTid = Process.myTid();
        if (myTid == MyApplication.getMainThreadId()) {
            task.run();
        } else {
            MyApplication.getHandler().post(task);
        }
    }

    /**
     * 延迟执行一个任务-->在主线程中
     */
    public static void postTaskDelay(Runnable task, int delayMillis) {
        MyApplication.getHandler().postDelayed(task, delayMillis);
    }

    /**
     * 移除任务
     */
    public static void removeTask(Runnable task) {
        MyApplication.getHandler().removeCallbacks(task);
    }
}
