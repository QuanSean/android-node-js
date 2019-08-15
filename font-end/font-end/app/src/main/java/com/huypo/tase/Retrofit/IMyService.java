package com.huypo.tase.Retrofit;

import io.reactivex.Observable;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IMyService {



    @POST ("/user/login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password
    );

    @POST ("/user/register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("name") String name);

    @POST ("/project")
    @FormUrlEncoded
    Observable<String> createProject(@Header("token") String token,@Field("title") String title,
                                 @Field("description") String description,@Field("deadline") String deadline);

    @POST("/project/delete")
    @FormUrlEncoded
    Observable<String> deleteProject(@Header("token") String token,@Field("id") String id);

    @POST("/project/done")
    @FormUrlEncoded
    Observable<String> changeStatusDoneProject(@Header("token") String token,@Field("id") String id);

    @POST("/project/task/list")
    @FormUrlEncoded
    Observable<String> showListTask(@Header("token") String token,@Field("id") String id);

    @POST("/project/task")
    @FormUrlEncoded
    Observable<String> addTask(@Header("token") String token, @Field("id") String id, @Field("title") String title,@Field("deadline") String deadline);

    @POST("/project/task/item")
    @FormUrlEncoded
    Observable<String> addItemTask(@Header("token") String token, @Field("title") String title,@Field("idProject") String idProject, @Field("idTask") int idTask);

    @POST("/project/partner")
    @FormUrlEncoded
    Observable<String> addEmailPartner(@Header("token") String token, @Field("id") String id,@Field("email") String email);

    @POST("/project/getProjectPartner")
    @FormUrlEncoded
    Observable<String> showListProjectPartner(@Header("token") String token);


    @GET("/project")
    @Headers({
            "Accept:application/json"
    })
    Observable<String> getProject(@Header("token") String token);





    @GET ("/user/demo")
    Observable<String> demo ();
}
