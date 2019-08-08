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

    private static Retrofit retrofit = null;
    public static Retrofit getInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(22, TimeUnit.SECONDS)
                .readTimeout(22, TimeUnit.SECONDS)
                .connectTimeout(22, TimeUnit.SECONDS)
                .build();


        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://tase-nodejs.herokuapp.com") //https://tase-nodejs.herokuapp.com
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        return retrofit;
    }
    public static IMyService getRetrofit(String token) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("x-access-token", token)
                    .method(original.method(),original.body());
            return  chain.proceed(builder.build());

        });


        return new Retrofit.Builder()
                .baseUrl("https://tase-nodejs.herokuapp.com/")
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMyService.class);
    }
}
