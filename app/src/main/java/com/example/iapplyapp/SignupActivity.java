package com.example.iapplyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    String stName, stEmail, stPassword;
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
                Intent i= new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

}