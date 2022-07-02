package com.es.netschool24;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://exnin.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
