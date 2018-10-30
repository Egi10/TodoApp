package com.todeapp.egifcb.todoapp.ui.register;

import android.support.annotation.NonNull;

import com.todeapp.egifcb.todoapp.api.APIConfig;
import com.todeapp.egifcb.todoapp.api.APIInterface;
import com.todeapp.egifcb.todoapp.api.APIRequest;
import com.todeapp.egifcb.todoapp.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RegisterPresenter {
    private RegisterView registerView;

    RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    void register(String email, String password) {
        registerView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        APIRequest apiRequest = new APIRequest();
        apiRequest.setEmail(email);
        apiRequest.setPassword(password);
        Call<APIResponse> apiResponseCall = apiInterface.register(apiRequest);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        registerView.onSuccess(response.body().getId(), response.body().getEmail(), response.headers().get("X-Auth"));
                    }
                    registerView.hideLoading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                registerView.onError(t.getMessage());
                registerView.hideLoading();
            }
        });
    }
}
