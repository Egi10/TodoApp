package com.todeapp.egifcb.todoapp.ui.detailtodos;

import com.todeapp.egifcb.todoapp.base.BaseView;
import com.todeapp.egifcb.todoapp.model.Todos;

interface DetailTodosView extends BaseView {
    void onSuccess(Todos todos);
    void onSuccessDelete(Todos todos);
    void onError(String message);
}
