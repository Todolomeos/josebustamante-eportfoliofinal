package com.example.weighttrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weighttracker.db";
    public static final int DATABASE_VERSION = 2;

    // Table for users
    public static final String TABLE_USERS = "users";
    public static final String COL_USER_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    // Table for weights
    public static final String TABLE_WEIGHTS = "weights";
    public static final String COL_WEIGHT_ID = "id";
    public static final String COL_WEIGHT_VALUE = "weight";
    public static final String COL_WEIGHT_DATE = "date";

    // Table for goal weight
    public static final String TABLE_GOAL = "goal";
    public static final String COL_GOAL_ID = "id";
    public static final String COL_GOAL_WEIGHT = "goal_weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create users table
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)");

        // Create weights table
        db.execSQL("CREATE TABLE " + TABLE_WEIGHTS + " (" +
                COL_WEIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_WEIGHT_VALUE + " REAL, " +
                COL_WEIGHT_DATE + " TEXT)");

        // Create goal weight table
        db.execSQL("CREATE TABLE " + TABLE_GOAL + " (" +
                COL_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_GOAL_WEIGHT + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Add the goal table when upgrading from version 1.
        if (oldVersion < 2) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GOAL + " (" +
                    COL_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_GOAL_WEIGHT + " REAL)");
        }
    }

    // Insert new user
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL(
                    "INSERT INTO " + TABLE_USERS + " (username, password) VALUES (?, ?)",
                    new Object[]{username, password}
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check login
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE username = ? AND password = ?",
                new String[]{username, password}
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();

        return exists;
    }

    // Check if username already exists
    public boolean userExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username}
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();

        return exists;
    }

    // Insert a weight entry into the database
    public boolean insertWeight(double weight, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL(
                    "INSERT INTO " + TABLE_WEIGHTS + " (" +
                            COL_WEIGHT_VALUE + ", " +
                            COL_WEIGHT_DATE + ") VALUES (?, ?)",
                    new Object[]{weight, date}
            );

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Read all weight entries from the database
    public Cursor getAllWeights() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_WEIGHTS +
                        " ORDER BY " + COL_WEIGHT_ID + " DESC",
                null
        );
    }

    // Delete a weight entry by ID
    public boolean deleteWeight(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_WEIGHTS,
                COL_WEIGHT_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return rowsDeleted > 0;
    }

    // Update a weight entry by ID
    public boolean updateWeight(int id, double weight) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT_VALUE, weight);

        int rowsUpdated = db.update(
                TABLE_WEIGHTS,
                values,
                COL_WEIGHT_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return rowsUpdated > 0;
    }

    // Save the user's goal weight
    public boolean saveGoalWeight(double goalWeight) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_GOAL, null, null);

        ContentValues values = new ContentValues();
        values.put(COL_GOAL_WEIGHT, goalWeight);

        long result = db.insert(TABLE_GOAL, null, values);

        return result != -1;
    }
    // Read the saved goal weight from the database.
    public double getGoalWeight() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COL_GOAL_WEIGHT + " FROM " + TABLE_GOAL + " LIMIT 1",
                null
        );

        double goalWeight = 0;

        if (cursor.moveToFirst()) {
            goalWeight = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COL_GOAL_WEIGHT)
            );
        }

        cursor.close();

        return goalWeight;
    }
}