package com.example.petofyReplica.model.pet;

import com.example.petofyReplica.model.Login.Header;
import com.example.petofyReplica.model.Login.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PetDetail {

    //  model class  wrt  POSTMAN response
    @SerializedName("header")
    @Expose
    private Header header;

    @SerializedName("response")
    @Expose
    private Response response;

    @SerializedName("data")
    @Expose
    private PetDetailData data;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public PetDetailData getData() {
        return data;
    }

    public void setData(PetDetailData data) {
        this.data = data;
    }
}
