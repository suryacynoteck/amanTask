package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPetid_petData {

    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
