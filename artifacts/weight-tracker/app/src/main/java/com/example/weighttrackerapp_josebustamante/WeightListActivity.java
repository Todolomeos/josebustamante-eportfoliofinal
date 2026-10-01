package com.example.weighttrackerapp_josebustamante;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WeightListActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private int userId;

    private TableLayout weightTable;
    private Button buttonAddWeight;
    private Button buttonAddGoal;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_list);

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

        weightTable = findViewById(R.id.weightTable);
        buttonAddWeight = findViewById(R.id.buttonAddWeight);
        buttonAddGoal = findViewById(R.id.buttonAddGoal);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonAddWeight.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WeightListActivity.this,
                    AddWeightActivity.class
            );

            intent.putExtra("USER_ID", userId);

            startActivity(intent);
        });

        buttonAddGoal.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WeightListActivity.this,
                    AddGoalActivity.class
            );

            intent.putExtra("USER_ID", userId);

            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WeightListActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (userId != -1) {
            loadWeights();
        }
    }

    private void loadWeights() {

        weightTable.removeAllViews();

        Cursor cursor = db.getWeightsForUser(userId);

        try {
        int idIndex = cursor.getColumnIndexOrThrow("id");
        int weightIndex = cursor.getColumnIndexOrThrow("weight");
        int dateIndex = cursor.getColumnIndexOrThrow("date");
        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    idIndex
            );

            double weight = cursor.getDouble(
                    weightIndex
            );

            String date = cursor.getString(
                    dateIndex
            );

            TableRow row = new TableRow(this);

            TextView dateView = new TextView(this);
            dateView.setText(date);
            dateView.setPadding(20, 20, 20, 20);

            TextView weightView = new TextView(this);
            weightView.setText(String.valueOf(weight));
            weightView.setPadding(20, 20, 20, 20);

            Button editButton = new Button(this);
            editButton.setText("EDIT");

            editButton.setOnClickListener(v -> {

                Intent intent = new Intent(
                        WeightListActivity.this,
                        AddWeightActivity.class
                );

                intent.putExtra("USER_ID", userId);
                intent.putExtra("WEIGHT_ID", id);
                intent.putExtra("WEIGHT_VALUE", weight);
                intent.putExtra("WEIGHT_DATE", date);

                startActivity(intent);
            });

            Button deleteButton = new Button(this);
            deleteButton.setText("DELETE");

            deleteButton.setOnClickListener(v -> {

                boolean deleted = db.deleteWeight(
                        id,
                        userId
                );

                if (deleted) {
                    loadWeights();
                }
            });

            row.addView(dateView);
            row.addView(weightView);
            row.addView(editButton);
            row.addView(deleteButton);

            weightTable.addView(row);
        }

        } finally {
            cursor.close();
        }
    }
}
