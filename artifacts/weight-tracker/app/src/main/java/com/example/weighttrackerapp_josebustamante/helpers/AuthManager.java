package com.example.weighttrackerapp_josebustamante.helpers;

import android.content.Context;

import com.example.weighttrackerapp_josebustamante.DatabaseHelper;

public class AuthManager {

    private final DatabaseHelper db;

    public AuthManager(Context context) {
        db = new DatabaseHelper(context);
    }

    public int login(String username, String password) {
        return db.getUserId(username, password);
    }

    public boolean createAccount(String username, String password) {

        if (db.userExists(username)) {
            return false;
        }

        return db.insertUser(username, password);
    }
}
