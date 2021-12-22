package com.example.petofyReplica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {

                    if (IsLoggedin.getLoginStatus(getBaseContext())) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }
                }

            }
        }; thread.start();

    }
}