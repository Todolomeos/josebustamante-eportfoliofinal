package com.example.weighttrackerapp_josebustamante;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddGoalActivity extends AppCompatActivity {

    private EditText goalWeightField;
    private Button saveGoalButton;
    private DatabaseHelper db;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_goal);

        db = new DatabaseHelper(this);

        userId = getIntent().getIntExtra("USER_ID", -1);

        if (userId == -1) {
            Toast.makeText(
                    this,
                    "User session not found.",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        goalWeightField = findViewById(R.id.goalWeightField);
        saveGoalButton = findViewById(R.id.buttonSaveGoal);

        double currentGoal = db.getGoalWeight(userId);

        if (currentGoal > 0) {
            goalWeightField.setText(String.valueOf(currentGoal));
        }

        saveGoalButton.setOnClickListener(v -> saveGoal());
    }

    private void saveGoal() {

        String goalText = goalWeightField.getText().toString().trim();

        if (goalText.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter a goal weight.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        double goalWeight;

        try {
            goalWeight = Double.parseDouble(goalText);
        } catch (NumberFormatException e) {
            Toast.makeText(
                    this,
                    "Please enter a valid goal weight.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (Double.isNaN(goalWeight) || Double.isInfinite(goalWeight) || goalWeight <= 0) {
            Toast.makeText(this, "Goal weight must be a positive finite number.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean saved = db.saveGoalWeight(
                userId,
                goalWeight
        );

        if (saved) {
            Toast.makeText(
                    this,
                    "Goal weight saved!",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {
            Toast.makeText(
                    this,
                    "Error saving goal weight.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
