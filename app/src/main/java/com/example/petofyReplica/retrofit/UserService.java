package com.example.petofyReplica.retrofit;

import com.example.petofyReplica.model.Login.LoginRequest;
import com.example.petofyReplica.model.Recycler.RecRequest;
import com.example.petofyReplica.model.Recycler.RecResponse;
import com.example.petofyReplica.model.pet.PetDetail;
import com.example.petofyReplica.model.pet.SetPetid;
import com.example.petofyReplica.model.Login.LoginResponse;

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
