package com.todeapp.egifcb.todoapp.ui.detailtodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.model.Todos;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;
import com.todeapp.egifcb.todoapp.ui.main.MainActivity;

public class DetailTodosActivity extends AppCompatActivity implements DetailTodosView {
    private CatLoadingView catLoadingView;
    private TextView tvCreator, tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todos);
        setTitle("Detail Todos");

        catLoadingView = new CatLoadingView();
        tvCreator = findViewById(R.id.tv_creator);
        tvText = findViewById(R.id.tv_text);

        DetailTodosPresenter detailTodosPresenter = new DetailTodosPresenter(this);
        UserPreferences userPreferences = new UserPreferences(getBaseContext());
        String token = userPreferences.getKeyToken();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        detailTodosPresenter.getDetail(token, id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSuccess(Todos todos) {
        tvCreator.setText(todos.getCreator());
        tvText.setText(todos.getText());
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        catLoadingView.show(getSupportFragmentManager(), "Mohon Menunggu");
    }

    @Override
    public void hideLoading() {
        catLoadingView.dismiss();
    }
}
