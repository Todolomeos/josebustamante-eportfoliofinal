package com.example.weighttrackerapp_josebustamante.helpers;

import android.content.Context;

import com.example.weighttrackerapp.DatabaseHelper;

// ### NEW: AuthManager handles all login-related logic.
// This keeps LoginActivity clean and follows good software design practices.
public class AuthManager {

    private DatabaseHelper db;

    // ### NEW: Constructor receives context and initializes DatabaseHelper
    public AuthManager(Context context) {
        db = new DatabaseHelper(context);
    }

    // ### NEW: Login method separated from UI
    public boolean login(String username, String password) {
        // Here we call the database to check if the user exists
        return db.checkUser(username, password);
    }

    // ### NEW: Create account method (optional enhancement)
    public boolean createAccount(String username, String password) {
        // Check if user already exists
        if (db.userExists(username)) {
            return false;
        }
        // Insert new user
        return db.insertUser(username, password);
    }
}
