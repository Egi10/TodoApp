package com.todeapp.egifcb.todoapp.model;

import com.google.gson.annotations.SerializedName;

public class Todos {
    @SerializedName("_id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("_creator")
    private String creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
