package com.example.weighttrackerapp_josebustamante;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weighttrackerapp_josebustamante.helpers.AuthManager;
import com.example.weighttrackerapp_josebustamante.helpers.ErrorHandler;
import com.example.weighttrackerapp_josebustamante.helpers.UIHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button createButton;
    private ProgressBar loginProgress;

    private AuthManager authManager;
    private ErrorHandler errorHandler;
    private UIHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authManager = new AuthManager(this);
        errorHandler = new ErrorHandler(this);
        uiHelper = new UIHelper(this);

        usernameInput = findViewById(R.id.editTextUsername);
        passwordInput = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        createButton = findViewById(R.id.buttonCreateAccount);
        loginProgress = findViewById(R.id.loginProgress);

        loginButton.setOnClickListener(v -> handleLogin());
        createButton.setOnClickListener(v -> handleCreateAccount());
    }

    private void handleLogin() {

        String user = usernameInput.getText().toString().trim();
        String pass = passwordInput.getText().toString().trim();

        if (!errorHandler.validateLoginFields(user, pass)) {
            return;
        }

        uiHelper.showLoading(loginProgress);

        int userId = authManager.login(user, pass);

        uiHelper.hideLoading(loginProgress);

        if (userId != -1) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    LoginActivity.this,
                    WeightListActivity.class
            );

            intent.putExtra("USER_ID", userId);

            startActivity(intent);

        } else {
            errorHandler.showError("Invalid username or password.");
        }
    }

    private void handleCreateAccount() {

        String user = usernameInput.getText().toString().trim();
        String pass = passwordInput.getText().toString().trim();

        if (!errorHandler.validateLoginFields(user, pass)) {
            return;
        }

        uiHelper.showLoading(loginProgress);

        boolean created = authManager.createAccount(user, pass);

        uiHelper.hideLoading(loginProgress);

        if (created) {
            Toast.makeText(
                    this,
                    "Account created!",
                    Toast.LENGTH_SHORT
            ).show();

        } else {
            errorHandler.showError("User already exists.");
        }
    }
}
