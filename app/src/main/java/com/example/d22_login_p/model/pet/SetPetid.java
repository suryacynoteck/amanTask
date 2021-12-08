package com.example.d22_login_p.model.pet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPetid {

    @SerializedName("data")
    @Expose
    private SetPetidpetData petData;

    public SetPetidpetData getPetData() {
        return petData;
    }

    public void setPetData(SetPetidpetData petData) {
        this.petData = petData;
    }
}
