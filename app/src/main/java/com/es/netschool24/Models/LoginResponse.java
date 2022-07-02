package com.es.netschool24.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("error")
    @Expose
    private boolean error;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}



