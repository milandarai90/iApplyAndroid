package com.example.iapplyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iapplyapp.model.UserModel;
import com.example.iapplyapp.retrofit.RetrofitService;
import com.example.iapplyapp.services.APIService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword, etCPassword;
    String stName, stEmail, stPassword, stCPassword;
    TextView login_txt;

//    @Nullable
    @Override
    public String getAttributionTag() {
        return super.getAttributionTag();
    }

    TextView signIn_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signIn_txt = findViewById(R.id.signIn_txt);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);

        Intent signinIntent = new Intent(this, LoginActivity.class);

        signIn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signinIntent);
            }
        });


        Button btSignUp= findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stName = etName.getText().toString();
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                stCPassword = etCPassword.getText().toString();
                APIService apiService = RetrofitService .getService(SignupActivity.this).create(APIService.class);
                UserModel userModel = new UserModel(stName, stEmail, stPassword, stCPassword);
                Call<UserModel> call = apiService.postSignup(userModel);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful()){
                            Log.v("signuppp", response.body().getMessage());
                            UserModel responseModel = response.body();
                            if(responseModel != null){
                                String message = responseModel.getMessage();
                                if(Objects.equals(message, "OTP sent to your email")){
                                    Toast.makeText(SignupActivity.this, "OTP Sent successfully", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(SignupActivity.this, OTPActivity.class);
                                    i.putExtra("name", stName);
                                    i.putExtra("email", stEmail);
                                    i.putExtra("password", stPassword);
                                    i.putExtra("c_password", stCPassword);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(SignupActivity.this, "Something is off.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable throwable) {
                        Toast.makeText(SignupActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}