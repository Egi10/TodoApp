package com.todeapp.egifcb.todoapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("users/login")
    Call<APIResponse> login(@Body APIRequest apiRequest);

    @POST("users")
    Call<APIResponse> register(@Body APIRequest apiRequest);

    @GET("todos/")
    Call<APIResponse> todos(@Header("x-auth") String auth);

    @POST("todos")
    Call<APIResponse> addTodos(@Header("x-auth") String auth,
                               @Body APIRequest apiRequest);

    @GET("todos/{id}")
    Call<APIResponse> getDetailTodos(@Header("x-auth") String auth,
                                     @Path("id") String id);

    @DELETE("todos/{id}")
    Call<APIResponse> deleteTodos(@Header("x-auth") String auth,
                                  @Path("id") String id);
}
