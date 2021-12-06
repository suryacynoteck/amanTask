package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecResponse {


    @SerializedName("header")
    @Expose
    private ResponseHeader header;

    @SerializedName("data")
    @Expose
    private RecyclerData data;

    @SerializedName("response")
    @Expose
    private Response response;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public RecyclerData getData() {
        return data;
    }

    public void setData(RecyclerData data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
