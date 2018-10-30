package com.todeapp.egifcb.todoapp.ui.main;

import android.support.annotation.NonNull;

import com.todeapp.egifcb.todoapp.api.APIConfig;
import com.todeapp.egifcb.todoapp.api.APIInterface;
import com.todeapp.egifcb.todoapp.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainPresenter {
    private MainView mainView;

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void getTodos(String token) {
        mainView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        Call<APIResponse> apiResponseCall = apiInterface.todos(token);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mainView.onSuccess(response.body().getListTodos());
                    }
                }
                mainView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                mainView.onError(t.getMessage());
                mainView.hideLoading();
            }
        });
    }
}
