package com.todeapp.egifcb.todoapp.ui.detailtodos;

import android.support.annotation.NonNull;

import com.todeapp.egifcb.todoapp.api.APIConfig;
import com.todeapp.egifcb.todoapp.api.APIInterface;
import com.todeapp.egifcb.todoapp.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class DetailTodosPresenter {
    private DetailTodosView detailTodosView;

    DetailTodosPresenter(DetailTodosView detailTodosView) {
        this.detailTodosView = detailTodosView;
    }

    void getDetail(String token, String id) {
        detailTodosView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        Call<APIResponse> apiResponseCall = apiInterface.getDetailTodos(token, id);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        detailTodosView.onSuccess(response.body().getTodos());
                    }
                }
                detailTodosView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                detailTodosView.onError(t.getMessage());
                detailTodosView.hideLoading();
            }
        });
    }

    void deleteDetail(String token, String id) {
        detailTodosView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        Call<APIResponse> apiResponseCall = apiInterface.deleteTodos(token, id);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        detailTodosView.onSuccessDelete(response.body().getTodos());
                    }
                }
                detailTodosView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                detailTodosView.onError(t.getMessage());
                detailTodosView.hideLoading();
            }
        });
    }
}
