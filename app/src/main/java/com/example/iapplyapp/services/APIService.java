package com.example.iapplyapp.services;

import com.example.iapplyapp.model.UserModel;

import kotlin.reflect.KCallable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface APIService {
    @POST("/api/login")
    Call<UserModel> postLogin(@Body UserModel userModel);

    @POST("/api/register")
    Call<UserModel> postSignup(@Body UserModel userModel);

    @POST("/api/verify_otp")
    Call<UserModel> verifyOTP(@Body UserModel userModel);
}
