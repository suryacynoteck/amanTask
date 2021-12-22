package com.example.petofyReplica;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class IsLoggedin {

    private static final String SHARED_PREF_NAME = "petofyReplica";
    private static final String SHARED_PREF_isLogin = "isLogin";

    public static boolean getLoginStatus(Context context) {

        boolean islogin;
        SharedPreferences sharedPreferences = context.getSharedPreferences("petofyReplica", MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("isLogin", false);

        return islogin;
    }



}
