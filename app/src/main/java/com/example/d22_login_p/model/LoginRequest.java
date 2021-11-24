package com.example.d22_login_p.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    String email;
    String password;


    public String getEmail() {
        return email;

    }

    @SerializedName("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @SerializedName("Password")
    public void setPassword(String password) {
        this.password = password;
    }
}
