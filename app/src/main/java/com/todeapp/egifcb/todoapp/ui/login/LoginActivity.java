package com.todeapp.egifcb.todoapp.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.todeapp.egifcb.todoapp.R;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                loginPresenter.login(email, password);
            }
        });
    }

    @Override
    public void onSuccess(String id, String email, String auth) {
        Toast.makeText(getBaseContext(), id + " - " + email + " - " + auth, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Log.d("onError", error);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
