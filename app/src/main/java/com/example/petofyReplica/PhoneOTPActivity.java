package com.example.petofyReplica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneOTPActivity extends AppCompatActivity {

    private CountryCodePicker ccp;
    private EditText ph_no, editText_otp;
    private Button btn_ph_send, btn_otp;
    private String final_phoneNo;
    private FirebaseAuth mAuth;
    private String otpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_otpactivity);


        ccp = findViewById(R.id.ccp);
        btn_ph_send = findViewById(R.id.btn_phNO_send);
        ph_no = findViewById(R.id.editTxt_phNo);

        btn_otp = findViewById(R.id.btn_otpValidate);
        editText_otp = findViewById(R.id.editTxt_otp);

        mAuth = FirebaseAuth.getInstance();


        ccp.registerCarrierNumberEditText(ph_no);
        Log.d("okok", "what's +ccp.toString() ");

        //todo: validation: enter a valid phNO.
        btn_ph_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final_phoneNo = ccp.getFullNumberWithPlus().replace(" ", "");

                Log.d("okok", "final phNO: "+final_phoneNo);
                initiateOTP();

            }
        });



        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText_otp.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Blank field", Toast.LENGTH_SHORT).show();
                } else if (editText_otp.getText().toString().length() != 6) {
                    Toast.makeText(getApplicationContext(), "Invalid otp", Toast.LENGTH_SHORT).show();
                } else {

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, editText_otp.getText().toString());
                    signInwithPhoneAuth(credential);

                }

            }
        });






    }

    private void initiateOTP() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                final_phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInwithPhoneAuth(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
        );

    }

    private void signInwithPhoneAuth(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(PhoneOTPActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Signin Code Error",Toast.LENGTH_LONG).show();
                        }


                    }
                });

    }
}