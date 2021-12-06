package com.example.d22_login_p.model;

import com.example.d22_login_p.model.pet.PetListData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecyclerData {

    @SerializedName("petList")
    @Expose
    private ArrayList<PetListData> petlist;

    @SerializedName("pagingHeader")
    @Expose
    private PagingHeader pagingHeader;


    public ArrayList<PetListData> getPetlist() {
        return petlist;
    }

    public void setPetlist(ArrayList<PetListData> petlist) {
        this.petlist = petlist;
    }

    public PagingHeader getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(PagingHeader pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

}
