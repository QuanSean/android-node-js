package com.huypo.tase.Retrofit;

import com.huypo.tase.Model.Respone;
import com.huypo.tase.Model.User;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {
    @POST("user/register")
    @FormUrlEncoded
    Observable<User> registerUser(@Field("email") String email,
                            @Field("name") String name,
                            @Field("password") String password);
    @POST("user/login")
    @FormUrlEncoded
    Observable<Respone> loginUser(@Field("email") String email,
                                  @Field("password")String password);
}
