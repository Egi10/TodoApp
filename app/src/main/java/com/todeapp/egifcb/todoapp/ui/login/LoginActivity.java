package com.todeapp.egifcb.todoapp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.roger.catloadinglibrary.CatLoadingView;
import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;
import com.todeapp.egifcb.todoapp.ui.main.MainActivity;
import com.todeapp.egifcb.todoapp.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private EditText etEmail;
    private EditText etPassword;
    private LoginPresenter loginPresenter;
    private UserPreferences userPreferences;
    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        TextView tvBuatDisini = findViewById(R.id.tv_buat_disini);
        Button btnLogin = findViewById(R.id.btn_login);

        userPreferences = new UserPreferences(getBaseContext());
        loginPresenter = new LoginPresenter(this);
        catLoadingView = new CatLoadingView();

        tvBuatDisini.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onSuccess(String id, String email, String auth) {
        userPreferences.setKeyId(id);
        userPreferences.setKeyMail(email);
        userPreferences.setKeyToken(auth);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
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
            case R.id.btn_login :
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                loginPresenter.login(email, password);
                break;

            case R.id.tv_buat_disini :
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
