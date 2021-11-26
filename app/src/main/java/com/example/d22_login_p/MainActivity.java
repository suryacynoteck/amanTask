package com.example.d22_login_p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d22_login_p.api_interface.ApiClient;
import com.example.d22_login_p.api_interface.UserService;
import com.example.d22_login_p.model.LoginRequest;
import com.example.d22_login_p.model.LoginResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText email;
    TextInputLayout password;
    TextView txt_forgot_pass , txt_Register;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


        email = findViewById(R.id.edit_txt_email);
        password = findViewById(R.id.edit_txt_pass);

        txt_forgot_pass = findViewById(R.id.txt_forgot_pass);
        txt_Register = findViewById(R.id.txt_Registernow);

        btn_login = findViewById(R.id.button);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (!validate_email() | !validate_password()) {
//                    return;
//                }


                login();


            }
        });



        //----------------x--forgot pass--x--------------xregisterx-------------------

        txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Registering...", Toast.LENGTH_SHORT).show();
            }
        });

        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "this is forgot password", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void login() {
        Log.d("okok", "inside login");

        LoginRequest loginRequest = new LoginRequest();
/*
        String email2 = email.getText().toString();
        String password2 = password.getEditText().getText().toString();



        Log.d("get", "email: " + email2);                     // working fine
        Log.d("get", "password: " + password2);
 */

//        loginRequest.getData().setEmail(email2);                                  //TODO:   find a sol.
//        Log.d("get", "after setting email" + loginRequest.getData().getEmail());




        Log.d("okok", "1");



//        loginRequest.setDeviceId("fa7dddece5deec1e"); // device id get
//        Toast.makeText(MainActivity.this, "Toast:1", Toast.LENGTH_SHORT).show();


//        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);  //  <<

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin("vet.petofy@gmail.com","pass@123");
        Log.d("okok", "2");
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Toast.makeText(MainActivity.this, "Toast:2", Toast.LENGTH_SHORT).show();
                Log.d("okok", "3");

                Log.d("okok", "4");
                LoginResponse loginResponse = response.body();



                Log.d("okok", "Response Code : " + response.code());

                //TODO:  response code 400 comming
                if (response.isSuccessful()) {

                    Log.d("okok", "Response Code : " + loginResponse.getData().getPhoneNumber());
                    Log.d("okok", "Response Code : " + loginResponse.getData().getFirstName());
                    Log.d("okok", "Response Code : " + loginResponse.getData().getAddress());

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean validate_password() {

        String passwordInput = password.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Fields can't be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    private boolean validate_email() {

        String emailInput = email.getEditableText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher( emailInput).matches()) {
            email.setError("enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

}