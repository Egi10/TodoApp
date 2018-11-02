package com.todeapp.egifcb.todoapp.ui.tambahtodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;
import com.todeapp.egifcb.todoapp.ui.main.MainActivity;

public class TambahTodosActivity extends AppCompatActivity implements TambahTodosView, View.OnClickListener {
    private TambahTodosPresenter tambahTodosPresenter;
    private UserPreferences userPreferences;
    private EditText etText;
    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_todos);
        setTitle("Tambah Todos");

        tambahTodosPresenter = new TambahTodosPresenter(this);
        userPreferences = new UserPreferences(getBaseContext());
        catLoadingView = new CatLoadingView();

        etText = findViewById(R.id.et_text);
        Button btnInput = findViewById(R.id.btn_input);

        btnInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input :
                String token = userPreferences.getKeyToken();
                String text = etText.getText().toString();

                tambahTodosPresenter.tambahTodos(token, text);

                break;
        }
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
    public void onSuccess(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
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
