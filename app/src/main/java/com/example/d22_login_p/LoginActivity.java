package com.example.d22_login_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d22_login_p.model.Login.LoginParams;
import com.example.d22_login_p.model.Login.LoginRequest;
import com.example.d22_login_p.model.Login.LoginResponse;
import com.example.d22_login_p.retrofit.ApiClient;

import com.example.d22_login_p.retrofit.UserService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText email;
    TextInputLayout password;
    TextView txt_forgot_pass, txt_Register;
    Button btn_login;
    private String email_user, pass_user;
    private TextInputEditText pass_edt;
    private UserService apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiInterface = ApiClient.getClient(this).create(UserService.class);       // todo:  declared public static final


        email = findViewById(R.id.edit_txt_email);
        password = findViewById(R.id.edit_txt_pass);
        pass_edt = findViewById(R.id.pass_edt);

        txt_forgot_pass = findViewById(R.id.txt_forgot_pass);
        txt_Register = findViewById(R.id.txt_Registernow);

        btn_login = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_user = email.getText().toString();
                pass_user = pass_edt.getText().toString();

                if (!validate_email() | !validate_password()) {
                    return;
                }

                LoginRequest loginRequest = new LoginRequest();
                LoginParams params = new LoginParams();

                params.setEmail(email_user);
                params.setPassword(pass_user);
                loginRequest.setData(params);       //   <<--

                if (isOnline()) {
                    login(loginRequest);
                }



            }
        });

        //----------------x--forgot pass--x--------------xregisterx-------------------
        txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Registering...", Toast.LENGTH_SHORT).show();
            }
        });
        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "this is forgot password", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {

            Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private void login(LoginRequest loginReqParam) {

        progressBar.setVisibility(View.VISIBLE);
        Call<LoginResponse> loginResponseCall = apiInterface.userLogin(loginReqParam);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {

                    LoginResponse loginResponse = response.body();

                    Log.d("okok", "onResponse: "+loginResponse.toString());
                    Log.d("okok", "onResponse: "+loginResponse.getResponse().getResponseCode());
                    Log.d("okok", "Response Code : " + loginResponse.getData().getPhoneNumber());

                    Log.d("okok", "Response Code : " + loginResponse.getData().getFirstName());
                    Log.d("okok", "Email : " + loginResponse.getData().getEmail());
                    Log.d("okok", "token: " + loginResponse.getResponse().getToken());

                    String token = loginResponse.getResponse().getToken().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.apply();


                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("head_txt", loginResponse.getData().getFirstName());
                    intent.putExtra("subhead_txt", loginResponse.getData().getEmail());
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
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
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

}