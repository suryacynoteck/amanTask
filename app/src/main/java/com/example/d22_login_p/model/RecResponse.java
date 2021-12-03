package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecResponse {


    @SerializedName("header")
    @Expose
    private Rec_Header header;

    @SerializedName("data")
    @Expose
    private Rec_Data data;

    @SerializedName("response")
    @Expose
    private Rec_Response response;

    public Rec_Header getHeader() {
        return header;
    }

    public void setHeader(Rec_Header header) {
        this.header = header;
    }

    public Rec_Data getData() {
        return data;
    }

    public void setData(Rec_Data data) {
        this.data = data;
    }

    public Rec_Response getResponse() {
        return response;
    }

    public void setResponse(Rec_Response response) {
        this.response = response;
    }
}
