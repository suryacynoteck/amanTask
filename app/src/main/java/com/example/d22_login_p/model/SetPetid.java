package com.example.d22_login_p.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPetid {

    @SerializedName("data")
    @Expose
    private SetPetid_petData petData;

    public SetPetid_petData getPetData() {
        return petData;
    }

    public void setPetData(SetPetid_petData petData) {
        this.petData = petData;
    }
}
