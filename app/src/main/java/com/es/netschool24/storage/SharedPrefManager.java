package com.es.netschool24.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.es.netschool24.Models.LoginResponse;

public class SharedPrefManager {

    public static final String SHARED_PREF_NAME = "my_shared_pref";
    private static SharedPrefManager mInstance;
    private Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public void saveUser(LoginResponse loginResponse) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", loginResponse.getToken());
        editor.apply();
    }


    public static void saveUserID(int userId, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("userId", userId);
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", String.valueOf(-1)) != String.valueOf(-1);
    }

    public void LoggedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


    }
}
