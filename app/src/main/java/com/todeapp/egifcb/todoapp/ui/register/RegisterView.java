package com.todeapp.egifcb.todoapp.ui.register;

import com.todeapp.egifcb.todoapp.base.BaseView;

public interface RegisterView extends BaseView {
    void onSuccess(String id, String email, String auth);
    void onError(String error);
}
