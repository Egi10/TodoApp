package com.todeapp.egifcb.todoapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private String keyId = "_id";
    private String keyMail = "email";
    private String keyToken = "token";
    private String islogin = "is_login";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserPreferences(Context context) {
        String PREFS_NAME = "UserPref";
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getKeyId() {
        return sharedPreferences.getString(keyId, null);
    }

    public void setKeyId(String keyId) {
        editor = sharedPreferences.edit();
        editor.putString(this.keyId, keyId);
        editor.apply();
    }

    public String getKeyMail() {
        return sharedPreferences.getString(keyMail, null);
    }

    public void setKeyMail(String keyMail) {
        editor = sharedPreferences.edit();
        editor.putString(this.keyMail, keyMail);
        editor.apply();
    }

    public String getKeyToken() {
        return sharedPreferences.getString(keyToken, null);
    }

    public void setKeyToken(String keyToken) {
        editor = sharedPreferences.edit();
        editor.putBoolean(islogin, true);
        editor.putString(this.keyToken, keyToken);
        editor.apply();
    }

    public boolean getIslogin() {
        return sharedPreferences.getBoolean(islogin, false);
    }
}
