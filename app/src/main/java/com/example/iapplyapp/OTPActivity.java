package com.example.iapplyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.iapplyapp.model.UserModel;
import com.example.iapplyapp.retrofit.RetrofitService;
import com.example.iapplyapp.services.APIService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {
    String name, email, password, c_password, otp_code, stotp1, stotp2, stotp3, stotp4 , stotp5 ;
    EditText otp1, otp2, otp3, otp4, otp5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otpactivity);
        Intent getIntent = getIntent();
        name = getIntent.getStringExtra("name");
        email = getIntent.getStringExtra("email");
        password = getIntent.getStringExtra("password");
        c_password = getIntent.getStringExtra("c_password");

        Button btVerify= findViewById(R.id.btVerify);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);


        btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stotp1 = otp1.getText().toString();
                stotp2 = otp2.getText().toString();
                stotp3 = otp3.getText().toString();
                stotp4 = otp4.getText().toString();
                stotp5 = otp5.getText().toString();
                otp_code = stotp1 + stotp2 + stotp3+stotp4+stotp5;

                APIService apiService = RetrofitService.getService(OTPActivity.this).create(APIService.class);
                UserModel userModel = new UserModel(name, email, password, otp_code, c_password);
                Call<UserModel> call = apiService.verifyOTP(userModel);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful()){
                            UserModel responseModel = response.body();
                            if(responseModel != null){
                                String message = responseModel.getMessage();
                                if(Objects.equals(message, "User registered successfully")){
                                    Toast.makeText(OTPActivity.this, "User registered successfully.Login Now", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(OTPActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable throwable) {

                    }
                });
            }
        });
    }
}