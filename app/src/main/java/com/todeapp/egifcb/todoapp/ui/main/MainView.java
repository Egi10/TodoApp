package com.todeapp.egifcb.todoapp.ui.main;

import com.todeapp.egifcb.todoapp.base.BaseView;
import com.todeapp.egifcb.todoapp.model.Todos;

import java.util.ArrayList;

public interface MainView extends BaseView {
    void onSuccess(ArrayList<Todos> list);
    void onError(String error);
}
