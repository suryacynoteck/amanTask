package com.example.petofyReplica.model.Recycler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecResponseData {

    @SerializedName("petList")
    @Expose
    private ArrayList<RecDataPetlist> petlist;

    @SerializedName("pagingHeader")
    @Expose
    private RecDataPagingHeader pagingHeader;


    public ArrayList<RecDataPetlist> getPetlist() {
        return petlist;
    }

    public void setPetlist(ArrayList<RecDataPetlist> petlist) {
        this.petlist = petlist;
    }

    public RecDataPagingHeader getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(RecDataPagingHeader pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

}
