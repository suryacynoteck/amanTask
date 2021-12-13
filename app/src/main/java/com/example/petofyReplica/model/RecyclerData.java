package com.example.petofyReplica.model;

import com.example.petofyReplica.model.Recycler.RecDataPetlist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecyclerData {

    @SerializedName("petList")
    @Expose
    private ArrayList<RecDataPetlist> petlist;

    @SerializedName("pagingHeader")
    @Expose
    private PagingHeader pagingHeader;


    public ArrayList<RecDataPetlist> getPetlist() {
        return petlist;
    }

    public void setPetlist(ArrayList<RecDataPetlist> petlist) {
        this.petlist = petlist;
    }

    public PagingHeader getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(PagingHeader pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

}
