package com.todeapp.egifcb.todoapp.api;

import android.support.annotation.NonNull;

import com.todeapp.egifcb.todoapp.preferences.UserPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private static Retrofit getRetrofitClientAuth(final String auth) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("x-auth", auth).build();
                        return chain.proceed(request);
                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl("https://murmuring-temple-99362.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static APIInterface getConfigAuth(String auth) {
        return getRetrofitClientAuth(auth).create(APIInterface.class);
    }
}
