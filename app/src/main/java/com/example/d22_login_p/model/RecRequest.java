package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecRequest {

    @SerializedName("data")
    @Expose
    private RecReqestParams recReqestParams;

    public RecRequest(RecReqestParams recReqestParams) {
        this.recReqestParams = recReqestParams;
    }

    public RecReqestParams getRecReqestParams() {
        return recReqestParams;
    }

    public void setRecReqestParams(RecReqestParams recReqestParams) {
        this.recReqestParams = recReqestParams;
    }



}

