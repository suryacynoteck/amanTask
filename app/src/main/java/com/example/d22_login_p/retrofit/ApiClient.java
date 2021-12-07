package com.example.d22_login_p.retrofit;


import android.app.Activity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    // could be,, send parameter 1,2  via  getClient >> then  if 2 , use baseUrl2  for building Retrofit instance

    private static final String API_BASE_URL = "https://petofyapi.azurewebsites.net/api/";

    private static Retrofit retrofitAuthenticated = null;

        public static Retrofit getClient ( final Activity activity){

            if (retrofitAuthenticated == null) {

                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                OkHttpClient clientAuthenticated;
                builder.connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .interceptors().add(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {  // no usage in this project

                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest
                                .newBuilder()
                                .header("Content-Type", "application/json")
                                .method(originalRequest.method(), originalRequest.body());
                        return chain.proceed(builder.build());
                    }
                });

                    clientAuthenticated = builder.build();
                    retrofitAuthenticated = new Retrofit.Builder()
                            .client(clientAuthenticated)
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

            }
            return retrofitAuthenticated;

        }

    }

