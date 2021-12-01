package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginParams {

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("DeviceId")
    @Expose
    private String deviceId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}