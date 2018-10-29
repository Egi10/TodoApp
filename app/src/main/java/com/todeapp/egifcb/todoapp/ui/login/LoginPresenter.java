package com.todeapp.egifcb.todoapp.ui.login;


import android.support.annotation.NonNull;

import com.todeapp.egifcb.todoapp.api.APIConfig;
import com.todeapp.egifcb.todoapp.api.APIInterface;
import com.todeapp.egifcb.todoapp.api.APIRequest;
import com.todeapp.egifcb.todoapp.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresenter {
    private LoginView loginView;

    LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    void login(String email, String password) {
        loginView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        APIRequest apiRequest = new APIRequest();
        apiRequest.setEmail(email);
        apiRequest.setPassword(password);
        Call<APIResponse> responseCall = apiInterface.login(apiRequest);
        responseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        loginView.onSuccess(response.body().getId(), response.body().getEmail(), response.headers().get("X-Auth"));
                    }
                }
                loginView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                loginView.hideLoading();
                loginView.onError(t.getMessage());
            }
        });
    }
}
