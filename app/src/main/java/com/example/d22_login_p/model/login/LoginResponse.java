package com.example.d22_login_p.model.Login;

import com.example.d22_login_p.model.Login.Header;
import com.example.d22_login_p.model.Login.LoginResponseData;
import com.example.d22_login_p.model.Login.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("data")
    @Expose
    private LoginResponseData data;
    @SerializedName("response")
    @Expose
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }



    @Override
    public String toString() {
        return "LoginResponse{" +
                "header=" + header +
                ", data=" + data +
                ", response=" + response +
                '}';
    }
}
