package com.todeapp.egifcb.todoapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    private static Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl("https://murmuring-temple-99362.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIInterface getConfig() {
        return getRetrofitClient().create(APIInterface.class);
    }
}
