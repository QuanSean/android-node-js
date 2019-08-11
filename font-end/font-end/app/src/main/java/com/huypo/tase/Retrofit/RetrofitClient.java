package com.huypo.tase.Retrofit;

import com.google.gson.GsonBuilder;
import com.huypo.tase.Model.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    public static  Retrofit getInstance()
    {
        if (instance==null)

            instance= new Retrofit.Builder()
                    .baseUrl("http://192.168.1.110:2409/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


        return instance;
    }
}
