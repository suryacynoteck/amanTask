package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Rec_Data {

    @SerializedName("petList")
    @Expose
    private ArrayList<Rec_Data_Petlist> petlist;

    @SerializedName("pagingHeader")
    @Expose
    private Rec_Data_PagingHeader pagingHeader;


    public ArrayList<Rec_Data_Petlist> getPetlist() {
        return petlist;
    }

    public void setPetlist(ArrayList<Rec_Data_Petlist> petlist) {
        this.petlist = petlist;
    }

    public Rec_Data_PagingHeader getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(Rec_Data_PagingHeader pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

}
