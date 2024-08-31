package com.example.iapplyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.iapplyapp.model.UserModel;
import com.example.iapplyapp.retrofit.RetrofitService;
import com.example.iapplyapp.services.APIService;
import com.example.iapplyapp.utility.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    TextView register_txt;
    TextView forgotPassword;
    String stEmail, stPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Intent i= new Intent(LoginActivity.this, MainActivity.class);

        Button btLogin= findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("loginn","clicked");
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                APIService service = RetrofitService.getService(LoginActivity.this).create(APIService.class);
                UserModel userModel = new UserModel(null, null, stEmail,stPassword,null, null);
                Call<UserModel> call = service.postLogin(userModel);

                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        Log.v("loginn","responded");
                        if(response.isSuccessful()){
                            Log.v("loginn","success");
                            UserModel responseBody = response.body();
                             Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            TokenManager.saveToken(getApplicationContext(),responseBody.getToken());
                            startActivity(i);
                        }else{
                            Log.v("loginn","unsuccess");
                            Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable throwable) {
                        Log.v("loginn","failure");
//                        Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        register_txt = findViewById(R.id.register_txt);
        Intent registerIntent = new Intent(this, SignupActivity.class);

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerIntent);
            }
        });


        forgotPassword = findViewById(R.id.forgotPassword);
        Intent forgotIntent = new Intent(this, ForgotPasswordActivity.class);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(forgotIntent);
            }
        });



    }
}