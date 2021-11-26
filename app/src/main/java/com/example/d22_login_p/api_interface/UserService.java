package com.example.d22_login_p.api_interface;

import com.example.d22_login_p.model.LoginRequest;
import com.example.d22_login_p.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("User/Login")
//    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
    Call<LoginResponse> userLogin(@Field("Email") String email, @Field("Password") String password);

}
