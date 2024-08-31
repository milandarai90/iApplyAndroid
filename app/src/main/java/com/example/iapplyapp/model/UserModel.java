package com.example.iapplyapp.model;

public class UserModel {
    private String status, token, email, password, otp_code, message, c_password, name, otp;

    public UserModel() {
    }

    public UserModel(String name, String email, String password, String c_password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.c_password = c_password;
    }

    public UserModel(String name, String email, String password, String otp, String c_password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.otp = otp;
        this.c_password = c_password;
    }

    public UserModel(String status, String token, String email, String password, String otp_code, String message) {
        this.status = status;
        this.token = token;
        this.email = email;
        this.password = password;
        this.otp_code = otp_code;
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
