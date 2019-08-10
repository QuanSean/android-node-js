package com.huypo.tase.Retrofit;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IMyService {
    @POST ("/user/register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password") String password
    );


    @POST ("/user/login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password
    );



    @GET("/project")
    @Headers({
            "Accept:application/json"
    })
    Observable<String> getProject(@Header("token") String token);


    @GET ("/user/demo")
    Observable<String> demo ();
}
