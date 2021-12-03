package com.example.d22_login_p.model.Recycler;

import com.example.d22_login_p.model.Login.Header;
import com.example.d22_login_p.model.Login.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecResponse {


    @SerializedName("header")
    @Expose
    private Header header;

    @SerializedName("data")
    @Expose
    private RecResponseData data;

    @SerializedName("response")
    @Expose
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public RecResponseData getData() {
        return data;
    }

    public void setData(RecResponseData data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
