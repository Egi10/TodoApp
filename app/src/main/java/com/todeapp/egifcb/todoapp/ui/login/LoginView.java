package com.todeapp.egifcb.todoapp.ui.login;

import com.todeapp.egifcb.todoapp.base.BaseView;

interface LoginView extends BaseView {
    void onSuccess(String id, String email, String auth);
    void onError(String error);
}
