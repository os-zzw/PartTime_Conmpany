package com.zzw.john.parttime_conmpany.base;

/**
 * Created by ZheWei on 2016/9/14.
 */
public interface BaseInterFace {
    interface BaseView {
        void showMessage(String msg);

        void close();

        void showProgess(String msg);

        void hideProgess();

        void showErrorMessage();
    }

    interface BasePresenter<T extends BaseView> {
        void attachView(T view);

        void detachView();
    }
}
