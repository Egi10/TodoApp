package com.todeapp.egifcb.todoapp.api;

import com.google.gson.annotations.SerializedName;

public class APIResponse {
    @SerializedName("_id")
    private String id;

    @SerializedName("email")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
