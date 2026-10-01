package com.example.weighttrackerapp_josebustamante;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AddWeightActivity extends AppCompatActivity {

    private EditText editWeight;
    private EditText editDate;
    private Button buttonSave;

    private DatabaseHelper db;

    private int userId;
    private int weightId;
    private double oldWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        db = new DatabaseHelper(this);

        userId = getIntent().getIntExtra("USER_ID", -1);
        weightId = getIntent().getIntExtra("WEIGHT_ID", -1);
        oldWeight = getIntent().getDoubleExtra("WEIGHT_VALUE", -1);

        if (userId == -1) {
            Toast.makeText(
                    this,
                    "User session not found.",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        editWeight = findViewById(R.id.editWeight);
        editDate = findViewById(R.id.editDate);
        buttonSave = findViewById(R.id.buttonSaveWeight);

        if (weightId != -1) {
            editWeight.setText(String.valueOf(oldWeight));
            editDate.setText(getIntent().getStringExtra("WEIGHT_DATE"));
        }

        buttonSave.setOnClickListener(v -> saveWeight());
    }

    private void saveWeight() {

        String weightStr = editWeight.getText().toString().trim();
        String dateStr = editDate.getText().toString().trim();

        if (weightStr.isEmpty() || dateStr.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter both weight and date.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        double weight;

        try {
            weight = Double.parseDouble(weightStr);
        } catch (NumberFormatException e) {
            Toast.makeText(
                    this,
                    "Please enter a valid weight.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (Double.isNaN(weight) || Double.isInfinite(weight) || weight <= 0) {
            Toast.makeText(this, "Weight must be a positive finite number.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success;

        if (weightId != -1) {

            success = db.updateWeight(
                    weightId,
                    userId,
                    weight,
                    dateStr
            );

            if (success) {
                Toast.makeText(
                        this,
                        "Weight updated.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        this,
                        "Error updating weight.",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            success = db.insertWeight(
                    userId,
                    weight,
                    dateStr
            );

            if (success) {

                Toast.makeText(
                        this,
                        "Weight saved!",
                        Toast.LENGTH_SHORT
                ).show();

                checkGoalAndSendSMS(weight);

            } else {

                Toast.makeText(
                        this,
                        "Error adding weight.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }

        if (success) {
            finish();
        }
    }

    private void checkGoalAndSendSMS(double weight) {

        double goalWeight = db.getGoalWeight(userId);

        if (goalWeight > 0 && weight <= goalWeight) {

            SharedPreferences preferences =
                    getSharedPreferences(
                            "SMSSettings",
                            MODE_PRIVATE
                    );

            String phoneNumber =
                    preferences.getString(
                            "phoneNumber",
                            ""
                    );

            if (phoneNumber.isEmpty()) {

                Toast.makeText(
                        this,
                        "Add a phone number to receive goal alerts.",
                        Toast.LENGTH_LONG
                ).show();

                Intent intent = new Intent(
                        AddWeightActivity.this,
                        SMSPermissionActivity.class
                );

                startActivity(intent);
                return;
            }

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        this,
                        "SMS permission is required for goal alerts.",
                        Toast.LENGTH_LONG
                ).show();

                Intent intent = new Intent(
                        AddWeightActivity.this,
                        SMSPermissionActivity.class
                );

                startActivity(intent);
                return;
            }

            String message =
                    "Congratulations! You reached your goal weight.";

            try {

                SmsManager smsManager =
                        SmsManager.getDefault();

                smsManager.sendTextMessage(
                        phoneNumber,
                        null,
                        message,
                        null,
                        null
                );

                Toast.makeText(
                        this,
                        "Goal SMS sent!",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Unable to send SMS.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}
