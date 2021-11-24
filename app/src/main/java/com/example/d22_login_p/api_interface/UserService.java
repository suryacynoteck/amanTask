package com.example.d22_login_p.api_interface;

import com.example.d22_login_p.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {


    @POST("User/Login")
    Call<LoginRequest> userLogin(@Body LoginRequest loginRequest);

}
