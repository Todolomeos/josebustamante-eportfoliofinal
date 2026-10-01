package com.example.weighttrackerapp_josebustamante.helpers;

import android.content.Context;
import android.widget.Toast;

// ### NEW: ErrorHandler centralizes validation and error messages.
// This keeps LoginActivity clean and improves maintainability.
public class ErrorHandler {

    private Context context;

    // ### NEW: Constructor receives context for showing Toast messages
    public ErrorHandler(Context context) {
        this.context = context;
    }

    // ### NEW: Validate login fields (username and password)
    public boolean validateLoginFields(String username, String password) {

        // Check if username is empty
        if (username.isEmpty()) {
            showError("Username cannot be empty.");
            return false;
        }

        // Check if password is empty
        if (password.isEmpty()) {
            showError("Password cannot be empty.");
            return false;
        }

        // Optional: Add more validation rules here (length, characters, etc.)

        return true; // Validation passed
    }

    // ### NEW: Show error message using Toast
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
