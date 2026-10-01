package com.example.weighttrackerapp_josebustamante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;   // ### NEW
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;   // ### NEW
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weighttrackerapp.DatabaseHelper;

// ### NEW: Import helper classes
import com.example.weighttrackerapp_josebustamante.helpers.AuthManager;
import com.example.weighttrackerapp_josebustamante.helpers.ErrorHandler;
import com.example.weighttrackerapp_josebustamante.helpers.UIHelper;

public class LoginActivity extends AppCompatActivity {

    // ### MODIFIED: Clearer variable names
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button createButton;

    // ### NEW: Loading indicator
    private ProgressBar loginProgress;

    // ### NEW: Architecture helpers
    private AuthManager authManager;
    private ErrorHandler errorHandler;
    private UIHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ### NEW: Initialize helpers
        authManager = new AuthManager(this);
        errorHandler = new ErrorHandler(this);
        uiHelper = new UIHelper(this);

        // ### MODIFIED: Initialize UI elements
        usernameInput = findViewById(R.id.editTextUsername);
        passwordInput = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        createButton = findViewById(R.id.buttonCreateAccount);
        loginProgress = findViewById(R.id.loginProgress);

        // ### MODIFIED: Login button with improved validation
        loginButton.setOnClickListener(v -> handleLogin());

        // ### NEW: Create account directly (Option 2)
        createButton.setOnClickListener(v -> handleCreateAccount());
    }

    // ### NEW: Login logic
    private void handleLogin() {

        String user = usernameInput.getText().toString().trim();
        String pass = passwordInput.getText().toString().trim();

        // Validate fields
        if (!errorHandler.validateLoginFields(user, pass)) {
            return;
        }

        // Show loading
        uiHelper.showLoading(loginProgress);

        // Authenticate
        boolean valid = authManager.login(user, pass);

        // Hide loading
        uiHelper.hideLoading(loginProgress);

        if (valid) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, WeightListActivity.class);
            startActivity(intent);

        } else {
            errorHandler.showError("Invalid username or password.");
        }
    }

    // ### NEW: Create account logic (Option 2)
    private void handleCreateAccount() {

        String user = usernameInput.getText().toString().trim();
        String pass = passwordInput.getText().toString().trim();

        // Validate fields
        if (!errorHandler.validateLoginFields(user, pass)) {
            return;
        }

        // Show loading
        uiHelper.showLoading(loginProgress);

        // Try to create account
        boolean created = authManager.createAccount(user, pass);

        // Hide loading
        uiHelper.hideLoading(loginProgress);

        if (created) {
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
        } else {
            errorHandler.showError("User already exists.");
        }
    }
}
