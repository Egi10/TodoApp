package com.todeapp.egifcb.todoapp.ui.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.adapter.TodosAdapter;
import com.todeapp.egifcb.todoapp.model.Todos;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{
    private RecyclerView recyclerView;
    private ArrayList<Todos> listTodos;
    private TodosAdapter todosAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String token;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        UserPreferences userPreferences = new UserPreferences(getBaseContext());
        token = userPreferences.getKeyToken();
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        listTodos = new ArrayList<>();

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    @Override
    public void onSuccess(ArrayList<Todos> list) {
        listTodos.clear();
        listTodos.addAll(list);
        todosAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadData() {
        mainPresenter.getTodos(token);
        todosAdapter = new TodosAdapter(getBaseContext());
        todosAdapter.setTodos(listTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todosAdapter);
    }
}
