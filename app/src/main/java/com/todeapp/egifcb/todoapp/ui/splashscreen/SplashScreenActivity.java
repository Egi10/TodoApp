package com.todeapp.egifcb.todoapp.ui.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.preferences.UserPreferences;
import com.todeapp.egifcb.todoapp.ui.login.LoginActivity;
import com.todeapp.egifcb.todoapp.ui.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userPreferences = new UserPreferences(getBaseContext());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userPreferences.getIslogin()) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
