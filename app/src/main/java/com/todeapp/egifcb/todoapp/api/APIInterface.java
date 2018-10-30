package com.todeapp.egifcb.todoapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("users/login")
    Call<APIResponse> login(@Body APIRequest apiRequest);

    @POST("users")
    Call<APIResponse> register(@Body APIRequest apiRequest);
}
