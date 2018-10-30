package com.todeapp.egifcb.todoapp.ui.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {
    private RegisterPresenter registerPresenter;
    private EditText etEmail;
    private EditText etPassword;
    private UserPreferences userPreferences;
    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);
        userPreferences = new UserPreferences(getBaseContext());
        catLoadingView = new CatLoadingView();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onSuccess(String id, String email, String auth) {
        userPreferences.setKeyId(id);
        userPreferences.setKeyMail(email);
        userPreferences.setKeyToken(auth);

        Toast.makeText(getBaseContext(), email, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Log.d("onError", error);
    }

    @Override
    public void showLoading() {
        catLoadingView.show(getSupportFragmentManager(), "Mohon Menunggu");
    }

    @Override
    public void hideLoading() {
        catLoadingView.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register :
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                registerPresenter.register(email, password);
                break;
        }
    }
}
