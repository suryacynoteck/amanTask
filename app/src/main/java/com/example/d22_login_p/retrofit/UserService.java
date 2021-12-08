package com.example.d22_login_p.retrofit;

import com.example.d22_login_p.model.Login.LoginRequest;
import com.example.d22_login_p.model.Recycler.RecRequest;
import com.example.d22_login_p.model.Recycler.RecResponse;
import com.example.d22_login_p.model.pet.PetDetail;
import com.example.d22_login_p.model.pet.SetPetid;
import com.example.d22_login_p.model.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {


                             // LoginActivity  @POST req.
    @POST("User/Login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginparams);



                            // HomeFragment  @POST req.
    @POST("report/GetPetList")
    Call<RecResponse> get_petData(@Body RecRequest recRequest, @Header("Authorization") String token);



                            // PetCardFragment  @POST req.
    @POST("pet/GetPetDetail")
    Call<PetDetail> getpetDetail(@Body SetPetid setPetid, @Header("Authorization") String token);



}
