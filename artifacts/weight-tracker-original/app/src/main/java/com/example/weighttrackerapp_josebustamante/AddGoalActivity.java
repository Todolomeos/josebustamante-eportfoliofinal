package com.example.weighttrackerapp_josebustamante;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weighttrackerapp.DatabaseHelper;

public class AddGoalActivity extends AppCompatActivity {

    EditText goalWeightField;
    Button saveGoalButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this Activity to the Add Goal screen.
        setContentView(R.layout.activity_add_goal);

        // Open the database.
        db = new DatabaseHelper(this);

        // Find the goal weight field and Save button.
        goalWeightField = findViewById(R.id.goalWeightField);
        saveGoalButton = findViewById(R.id.buttonSaveGoal);

        // Save the goal weight when the user presses Save.
        saveGoalButton.setOnClickListener(v -> {

            String goalText = goalWeightField.getText().toString().trim();

            // Make sure the user entered a goal weight.
            if (goalText.isEmpty()) {
                Toast.makeText(
                        this,
                        "Please enter a goal weight",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            double goalWeight = Double.parseDouble(goalText);

            // Save the goal weight in the database.
            boolean saved = db.saveGoalWeight(goalWeight);

            if (saved) {
                Toast.makeText(
                        this,
                        "Goal weight saved!",
                        Toast.LENGTH_SHORT
                ).show();

                // Return to the weight list screen.
                finish();

            } else {
                Toast.makeText(
                        this,
                        "Error saving goal weight",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}