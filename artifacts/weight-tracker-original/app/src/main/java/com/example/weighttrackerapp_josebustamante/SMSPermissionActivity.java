package com.example.weighttrackerapp_josebustamante;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSPermissionActivity extends AppCompatActivity {

    private static final int SMS_REQUEST_CODE = 100;
    private EditText phoneNumberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_permission);

        phoneNumberField = findViewById(R.id.phoneNumberField);

        Button btnAllowSMS = findViewById(R.id.button8);
        Button btnDeny = findViewById(R.id.button9);

        btnAllowSMS.setOnClickListener(v -> {

            String phoneNumber =
                    phoneNumberField.getText().toString().trim();

            if (phoneNumber.isEmpty()) {
                Toast.makeText(
                        this,
                        "Please enter a phone number",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Save the phone number for SMS alerts.
            SharedPreferences preferences =
                    getSharedPreferences("SMSSettings", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("phoneNumber", phoneNumber);
            editor.apply();

            requestSMSPermission();
        });

        // Close the screen if the user does not want SMS notifications.
        btnDeny.setOnClickListener(v -> finish());
    }

    private void requestSMSPermission() {

        // Check if SMS permission has already been granted.
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
        ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_REQUEST_CODE
            );

        } else {
            Toast.makeText(
                    this,
                    "SMS permission already granted",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ) {
        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        if (requestCode == SMS_REQUEST_CODE) {

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        this,
                        "SMS permission granted!",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "SMS permission denied",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        }
    }
}