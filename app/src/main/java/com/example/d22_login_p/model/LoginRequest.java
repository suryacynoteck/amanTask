package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("data")
    @Expose
    private LoginParams data;

    public LoginParams getData() {
        return data;
    }

    public void setData(LoginParams data) {
        this.data = data;
    }



}
