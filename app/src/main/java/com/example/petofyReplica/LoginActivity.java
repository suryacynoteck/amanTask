package com.example.petofyReplica;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petofyReplica.common_functionality.Internet_permission;
import com.example.petofyReplica.model.Login.LoginParams;
import com.example.petofyReplica.model.Login.LoginRequest;
import com.example.petofyReplica.model.Login.LoginResponse;
import com.example.petofyReplica.retrofit.ApiClient;

import com.example.petofyReplica.retrofit.UserService;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText email;
    private TextInputLayout password;
    private TextView txt_forgot_pass, txt_Register;
    private Button btn_login;
    private String email_user, pass_user;
    private TextInputEditText pass_edt;
    private UserService apiInterface;
    private ProgressBar progressBar;
    private ImageView google_Signin, fb_Signin;
    GoogleSignInClient mGoogleSignInClient;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    private String navHead_name;
//    private String navHead_email;     //todo: later pass 3 things
//    private String navHead_url;

    private Context context;
    private static int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiInterface = ApiClient.getClient(this).create(UserService.class);       // todo:  declared public static final

        context = getApplicationContext();          //  vs getBaseContext

        email = findViewById(R.id.edit_txt_email);
        password = findViewById(R.id.edit_txt_pass);
        pass_edt = findViewById(R.id.pass_edt);

        txt_forgot_pass = findViewById(R.id.txt_forgot_pass);
        txt_Register = findViewById(R.id.txt_Registernow);

        btn_login = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        google_Signin = findViewById(R.id.img_googleSignin);
        fb_Signin = findViewById(R.id.img_fbSignin);
        loginButton = findViewById(R.id.login_button);


        email.getBackground().setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.cardview_dark_background), PorterDuff.Mode.SRC_IN);
        pass_edt.getBackground().setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.purple_500), PorterDuff.Mode.SRC_IN);      //TODO: why not purple color, set  ( by def. black color only on password)


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

                if (Internet_permission.isOnline(context)) {
                    login(loginRequest);
                }


            }
        });


                                // ----------------x--FB sign in process>>----------------x--
        fb_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                com.facebook.login.widget.LoginButton btn = new com.facebook.login.widget.LoginButton(FacebookActivity.this);

                loginButton.performClick();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("okok", "FB user id " + loginResult.getAccessToken().getUserId());

                setFacebookData(loginResult);
                Toast.makeText(LoginActivity.this, "fb Login success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });


                                // ----------------x--Google sign in process>>----------------x--

// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        google_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlesignIn();
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

    private void setFacebookData(LoginResult loginResult) {
        Log.d("okok", "under set_FB_data");

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(@Nullable JSONObject object, @Nullable GraphResponse response) {

                        try {

                            Log.d("okok", "JSONObj info>>" + object.toString());

//                            String email = object.getString("email");         // email not extracting
                            String name = object.getString("name");
                            Log.d("okok", "name: " + name);


                            //todo: extract infor from profile

                            Log.d("okok", "Profile info>>");

                            if (Profile.getCurrentProfile() == null) {
                                profileTracker = new ProfileTracker() {
                                    @Override
                                    protected void onCurrentProfileChanged(@Nullable Profile oldprofile, @Nullable Profile currentprofile) {
                                        Log.d("okok", currentprofile.toString());
                                        Log.d("okok", currentprofile.getName().toString());
                                        Log.d("okok", currentprofile.getProfilePictureUri(200, 200).toString());

                                        navHead_name = currentprofile.getName().toString();

                                        start_MainActivity();
                                    }
                                };
//
                            } else {
                                Profile profile = Profile.getCurrentProfile();
                                Log.d("okok", profile.getName().toString());
                                Log.d("okok", profile.getProfilePictureUri(200, 200).toString());

                                navHead_name = profile.getName().toString();
                                start_MainActivity();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        Bundle parameter = new Bundle();
        parameter.putString("fields", "id,name,link");
        request.setParameters(parameter);
        request.executeAsync();

    }

    private void googlesignIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            if (account != null) {
                String personName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                String personEmail = account.getEmail();
                String personId = account.getId();
                Uri personPhoto = account.getPhotoUrl();

                Toast.makeText(this, "Signing in :" + personEmail, Toast.LENGTH_SHORT).show();

                navHead_name = personName;
                start_MainActivity();

            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }

    }

    private void login(LoginRequest loginReqParam) {

        progressBar.setVisibility(View.VISIBLE);
        Call<LoginResponse> loginResponseCall = apiInterface.userLogin(loginReqParam);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {

                    LoginResponse loginResponse = response.body();

                    Log.d("okok", "onResponse: " + loginResponse.toString());
                    Log.d("okok", "onResponse: " + loginResponse.getResponse().getResponseCode());
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

                    navHead_name = loginResponse.getData().getFirstName();

                    start_MainActivity();


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

    private void start_MainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("head_txt", navHead_name);
//        intent.putExtra("subhead_txt", loginResponse.getData().getEmail());
        startActivity(intent);

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