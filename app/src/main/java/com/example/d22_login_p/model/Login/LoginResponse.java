package com.example.d22_login_p.model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("header")
    @Expose
    private Header header;

    @SerializedName("response")
    @Expose
    private Response response;


    @SerializedName("data")
    @Expose
    private LoginResponseData data;


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
