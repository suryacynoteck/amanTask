package com.example.d22_login_p.model;

import android.widget.ImageView;

public class RecResponse {

// TODO: make it wrt the postman  ,,  Response,,   innerClass
    private int image;
    private String dogName;
    private String dogDOB;
    private String doctorName;

    public RecResponse(int image, String dogName, String dogDOB, String doctorName) {
        this.image = image;
        this.dogName = dogName;
        this.dogDOB = dogDOB;
        this.doctorName = doctorName;
    }

    //TODO:  only for testing ,, string dogName
    public RecResponse(String dogName) {
        this.dogName = dogName;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogDOB() {
        return dogDOB;
    }

    public void setDogDOB(String dogDOB) {
        this.dogDOB = dogDOB;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
