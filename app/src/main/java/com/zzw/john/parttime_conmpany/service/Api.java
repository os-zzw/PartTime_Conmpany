package com.zzw.john.parttime_conmpany.service;

import com.zzw.john.parttime_conmpany.bean.BaseBean;
import com.zzw.john.parttime_conmpany.bean.EmployerBeanAll;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by john on 2016/11/1.
 * 所有的 api接口
 */

public interface Api {
    //baseUri
    String API_SERVER_URL = "http://z160e02960.iask.in/webservice/";


    //注册
    @POST("employer/register")
    Observable<BaseBean> register(
            @Query("nickname") String nickname,
            @Query("password") String password
    );

    //登录
    @POST("employer/login")
    Observable<EmployerBeanAll> login(
            @Query("nickname") String nickname,
            @Query("password") String password
    );

    //发布兼职
}
