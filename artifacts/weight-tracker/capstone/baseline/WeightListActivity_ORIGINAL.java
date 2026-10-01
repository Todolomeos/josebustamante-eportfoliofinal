package com.example.weighttrackerapp_josebustamante;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weighttrackerapp.DatabaseHelper;

public class WeightListActivity extends AppCompatActivity {

    DatabaseHelper db;
    TableLayout weightTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this Activity to the weight list screen.
        setContentView(R.layout.activity_weight_list);

        // Open the database.
        db = new DatabaseHelper(this);

        // Find the table that displays the saved weights.
        weightTable = findViewById(R.id.weightTable);

        // Find the Add Weight button.
        Button addWeightButton = findViewById(R.id.buttonAddWeight);

        // Open the Add Weight screen.
        addWeightButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    WeightListActivity.this,
                    AddWeightActivity.class
            );
            startActivity(intent);
        });

        // Find the Add Goal Weight button.
        Button addGoalButton = findViewById(R.id.buttonAddGoal);

        // Open the Add Goal Weight screen.
        addGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    WeightListActivity.this,
                    AddGoalActivity.class
            );
            startActivity(intent);
        });

        // Find the Logout button.
        Button logoutButton = findViewById(R.id.buttonLogout);

        // Return to the Login screen.
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    WeightListActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });

        // Display the saved weights.
        loadWeights();

        // Open the SMS permission screen.
        Intent smsIntent = new Intent(
                WeightListActivity.this,
                SMSPermissionActivity.class
        );
        startActivity(smsIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the weight list when returning to this screen.
        loadWeights();
    }

    private void loadWeights() {

        // Keep the header row and remove old data rows.
        if (weightTable.getChildCount() > 1) {
            weightTable.removeViews(
                    1,
                    weightTable.getChildCount() - 1
            );
        }

        // Read all saved weights from the database.
        Cursor cursor = db.getAllWeights();

        if (cursor.moveToFirst()) {

            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COL_WEIGHT_ID
                        )
                );

                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COL_WEIGHT_DATE
                        )
                );

                double weight = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COL_WEIGHT_VALUE
                        )
                );

                // Create a new row for the weight entry.
                TableRow row = new TableRow(this);

                TextView dateView = new TextView(this);
                dateView.setText(date);
                dateView.setPadding(8, 8, 8, 8);

                TextView weightView = new TextView(this);
                weightView.setText(String.valueOf(weight));
                weightView.setPadding(8, 8, 8, 8);

                // Create an area for the Edit and Delete buttons.
                LinearLayout actionLayout = new LinearLayout(this);
                actionLayout.setOrientation(LinearLayout.HORIZONTAL);

                Button editButton = new Button(this);
                editButton.setText("Edit");

                Button deleteButton = new Button(this);
                deleteButton.setText("Delete");

                // Allow the user to update the selected weight.
                editButton.setOnClickListener(v -> {

                    EditText editWeight = new EditText(this);

                    editWeight.setInputType(
                            InputType.TYPE_CLASS_NUMBER |
                                    InputType.TYPE_NUMBER_FLAG_DECIMAL
                    );

                    editWeight.setText(String.valueOf(weight));

                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("Update Weight")
                            .setMessage("Enter the new weight:")
                            .setView(editWeight)
                            .setPositiveButton("Save", null)
                            .setNegativeButton("Cancel", null)
                            .create();

                    dialog.setOnShowListener(d -> {

                        Button saveButton = dialog.getButton(
                                AlertDialog.BUTTON_POSITIVE
                        );

                        saveButton.setOnClickListener(view -> {

                            String newWeightText =
                                    editWeight.getText().toString().trim();

                            if (newWeightText.isEmpty()) {
                                Toast.makeText(
                                        this,
                                        "Please enter a weight",
                                        Toast.LENGTH_SHORT
                                ).show();
                                return;
                            }

                            double newWeight =
                                    Double.parseDouble(newWeightText);

                            // Update the selected weight in the database.
                            boolean updated =
                                    db.updateWeight(id, newWeight);

                            if (updated) {
                                Toast.makeText(
                                        this,
                                        "Weight updated",
                                        Toast.LENGTH_SHORT
                                ).show();

                                dialog.dismiss();

                                // Refresh the table after updating the weight.
                                loadWeights();

                            } else {
                                Toast.makeText(
                                        this,
                                        "Error updating weight",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
                    });

                    dialog.show();
                });

                // Delete the selected weight from the database.
                deleteButton.setOnClickListener(v -> {

                    boolean deleted = db.deleteWeight(id);

                    if (deleted) {
                        Toast.makeText(
                                this,
                                "Weight deleted",
                                Toast.LENGTH_SHORT
                        ).show();

                        // Refresh the table after deleting the weight.
                        loadWeights();

                    } else {
                        Toast.makeText(
                                this,
                                "Error deleting weight",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });

                actionLayout.addView(editButton);
                actionLayout.addView(deleteButton);

                row.addView(dateView);
                row.addView(weightView);
                row.addView(actionLayout);

                weightTable.addView(row);

            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}