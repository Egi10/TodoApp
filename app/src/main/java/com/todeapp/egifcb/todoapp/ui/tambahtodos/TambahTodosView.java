package com.todeapp.egifcb.todoapp.ui.tambahtodos;

import com.todeapp.egifcb.todoapp.base.BaseView;

public interface TambahTodosView extends BaseView {
    void onSuccess(String message);
    void onError(String error);
}
