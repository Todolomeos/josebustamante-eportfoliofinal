package com.example.weighttrackerapp_josebustamante;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weighttracker.db";
    public static final int DATABASE_VERSION = 3;

    // USERS TABLE
    public static final String TABLE_USERS = "users";
    public static final String COL_USER_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    // WEIGHTS TABLE
    public static final String TABLE_WEIGHTS = "weights";
    public static final String COL_WEIGHT_ID = "id";
    public static final String COL_WEIGHT_USER_ID = "user_id";
    public static final String COL_WEIGHT_VALUE = "weight";
    public static final String COL_WEIGHT_DATE = "date";

    // GOAL TABLE
    public static final String TABLE_GOAL = "goal";
    public static final String COL_GOAL_ID = "id";
    public static final String COL_GOAL_WEIGHT = "goal_weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // USERS
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)");

        // WEIGHTS (NOW WITH user_id)
        db.execSQL("CREATE TABLE " + TABLE_WEIGHTS + " (" +
                COL_WEIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_WEIGHT_USER_ID + " INTEGER, " +
                COL_WEIGHT_VALUE + " REAL, " +
                COL_WEIGHT_DATE + " TEXT)");

        // GOAL
        db.execSQL("CREATE TABLE " + TABLE_GOAL + " (" +
                COL_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_GOAL_WEIGHT + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE " + TABLE_WEIGHTS + " ADD COLUMN " + COL_WEIGHT_USER_ID + " INTEGER");
        }
    }

    // INSERT USER
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO " + TABLE_USERS + " (username, password) VALUES (?, ?)",
                    new Object[]{username, password});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // CHECK LOGIN
    public int checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id FROM " + TABLE_USERS + " WHERE username = ? AND password = ?",
                new String[]{username, password}
        );

        int userId = -1;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }

        cursor.close();
        return userId;
    }

    // CHECK IF USER EXISTS
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

    // INSERT WEIGHT (NOW WITH user_id)
    public boolean insertWeight(int userId, double weight, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO " + TABLE_WEIGHTS +
                            " (user_id, weight, date) VALUES (?, ?, ?)",
                    new Object[]{userId, weight, date});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // GET WEIGHTS ONLY FOR THIS USER
    public Cursor getWeightsByUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_WEIGHTS +
                        " WHERE user_id = ? ORDER BY id DESC",
                new String[]{String.valueOf(userId)}
        );
    }

    // DELETE WEIGHT
    public boolean deleteWeight(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_WEIGHTS,
                COL_WEIGHT_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return rowsDeleted > 0;
    }

    // UPDATE WEIGHT
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

    // SAVE GOAL
    public boolean saveGoalWeight(double goalWeight) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_GOAL, null, null);

        ContentValues values = new ContentValues();
        values.put(COL_GOAL_WEIGHT, goalWeight);

        long result = db.insert(TABLE_GOAL, null, values);

        return result != -1;
    }

    // GET GOAL
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
