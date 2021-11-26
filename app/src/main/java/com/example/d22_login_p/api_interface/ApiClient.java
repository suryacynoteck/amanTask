package com.example.d22_login_p.api_interface;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit() {

        okhttp3.logging.HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://petofyapi.azurewebsites.net/api/")
                .client(okHttpClient)
                .build();

        return retrofit;

    }

    public static UserService getUserService() {

        UserService userService = getRetrofit().create(UserService.class);
        return userService;

    }


}
