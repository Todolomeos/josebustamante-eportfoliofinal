package com.example.weighttrackerapp_josebustamante;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.weighttrackerapp.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddWeightActivity extends AppCompatActivity {

    EditText weightField;
    Button saveButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this Activity to the Add Weight screen.
        setContentView(R.layout.activity_add_weight);

        // Open the database.
        db = new DatabaseHelper(this);

        // Find the weight field and Save button.
        weightField = findViewById(R.id.weightField);
        saveButton = findViewById(R.id.buttonSaveWeight);

        // Save the entered weight using today's date.
        saveButton.setOnClickListener(v -> {

            String weightText = weightField.getText().toString().trim();

            // Make sure the user entered a weight.
            if (weightText.isEmpty()) {
                Toast.makeText(
                        this,
                        "Please enter a weight",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            double weight = Double.parseDouble(weightText);

            String date = new SimpleDateFormat(
                    "MM/dd/yyyy",
                    Locale.getDefault()
            ).format(new Date());

            // Save the weight in the database.
            boolean inserted = db.insertWeight(weight, date);

            if (inserted) {

                Toast.makeText(
                        this,
                        "Weight saved!",
                        Toast.LENGTH_SHORT
                ).show();

                // Read the saved goal weight.
                double goalWeight = db.getGoalWeight();

                // Check if the user reached the goal weight.
                if (goalWeight > 0 && weight <= goalWeight) {

                    // Check if SMS permission is granted.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.SEND_SMS
                    ) == PackageManager.PERMISSION_GRANTED) {

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

                        // Send the SMS if a phone number was saved.
                        if (!phoneNumber.isEmpty()) {

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
                                        "Unable to send SMS",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                    }
                }

                // Return to the weight list after saving.
                finish();

            } else {

                Toast.makeText(
                        this,
                        "Error saving weight",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}