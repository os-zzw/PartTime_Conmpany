package com.zzw.john.parttime_conmpany.service;

import com.zzw.john.parttime_conmpany.bean.BaseBean;
import com.zzw.john.parttime_conmpany.bean.EmployerBeanAll;
import com.zzw.john.parttime_conmpany.bean.JobBean;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.observers.Observers;

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
    @POST("job/add")
    Observable<JobBean> add(
            @Query("employerID") String employerID,
            @Query("type") String type,
            @Query("name") String name,
            @Query("sex") String sex,
            @Query("salary") String salary,
            @Query("address") String address,
            @Query("remark") String remark,
            @Query("num") int num

    );

    //获得我发布的所有兼职
    @POST("job/queryAllMyJob/{employerID}")
    Observable<JobBean> queryAllMyJob(
            @Path("employerID") Integer employerID
    );

    //获得所有应聘人
    @POST("statusrecord/queryStatusRecordByEmployerID")
    Observable<JobBean> queryStatusRecordByEmployerID(
            @Query("employerID") Integer employerID
    );

}
