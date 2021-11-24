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
import com.example.d22_login_p.model.LoginRequest;
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

                if (!validate_email() | !validate_password()) {
                    return;
                }

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


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getEditText().getText().toString());



        Call<LoginRequest> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Log.d("okok", "Response Code : " + response.code());


                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
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