package com.example.d22_login_p.retrofit;

import com.example.d22_login_p.model.login.LoginRequest;
import com.example.d22_login_p.model.login.LoginResponse;
import com.example.d22_login_p.model.RecRequest;
import com.example.d22_login_p.model.RecResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {


    @POST("User/Login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginparams);


//    @Headers("Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjVkMGJkNmQ0LTIzNjQtNGU1Ny04Yzk1LTA3MzZlYTgwMDIyMSIsIm5iZiI6MTYzODI2NjkyOCwiZXhwIjoxNjY5ODAyOTI4LCJpYXQiOjE2MzgyNjY5Mjh9.4tRNX0vlPDEpR0kLG43JbtJ10-AZmONtpIIoXMk0Ksg")

    @POST("report/GetPetList")
    Call<RecResponse> get_petData(@Body RecRequest recRequest, @Header("Authorization") String token);
//    Call<List<RecResponse>> get_petData(@Body RecRequest recRequest,@Header("Authorization") String token );      // todo:  what iff ,, asked List<RecResponse>


  /*  @POST("pet/GetPetDetail")
    Call<PetDetail> getpetDetail(@Body SetPetid setPetid, @Header("Authorization") String token);*/


    /*
    {
"data": {
"PageNumber": 1,
"pageSize": 20
}
}
    * */

}
