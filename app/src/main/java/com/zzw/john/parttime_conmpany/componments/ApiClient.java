package com.zzw.john.parttime_conmpany.componments;


import com.zzw.john.parttime_conmpany.service.Api;

/**
 * Created by john(Zhewei) on 2016/11/29.
 */

public class ApiClient {

    private static Api mApi;

    private ApiClient() {
    }

    public static Api getApi() {
        if (mApi == null) {
            synchronized (ApiClient.class) {
                if (mApi == null) {
                    mApi = RetrofitClient.getmRetrofit().create(Api.class);
                }
            }
        }
        return mApi;
    }
}
