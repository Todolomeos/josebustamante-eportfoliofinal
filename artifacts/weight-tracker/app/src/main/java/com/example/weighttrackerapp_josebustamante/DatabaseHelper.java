package com.example.weighttrackerapp_josebustamante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weighttracker.db";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_USERS = "users";
    public static final String COL_USER_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    public static final String TABLE_WEIGHTS = "weights";
    public static final String COL_WEIGHT_ID = "id";
    public static final String COL_WEIGHT_USER_ID = "user_id";
    public static final String COL_WEIGHT_VALUE = "weight";
    public static final String COL_WEIGHT_DATE = "date";

    public static final String TABLE_GOAL = "goal";
    public static final String COL_GOAL_ID = "id";
    public static final String COL_GOAL_USER_ID = "user_id";
    public static final String COL_GOAL_WEIGHT = "goal_weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE_USERS + " (" +
                        COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_USERNAME + " TEXT UNIQUE NOT NULL, " +
                        COL_PASSWORD + " TEXT NOT NULL)"
        );

        db.execSQL(
                "CREATE TABLE " + TABLE_WEIGHTS + " (" +
                        COL_WEIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_WEIGHT_USER_ID + " INTEGER NOT NULL, " +
                        COL_WEIGHT_VALUE + " REAL NOT NULL, " +
                        COL_WEIGHT_DATE + " TEXT NOT NULL, " +
                        "FOREIGN KEY(" + COL_WEIGHT_USER_ID + ") REFERENCES " +
                        TABLE_USERS + "(" + COL_USER_ID + ") ON DELETE CASCADE)"
        );

        db.execSQL(
                "CREATE TABLE " + TABLE_GOAL + " (" +
                        COL_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_GOAL_USER_ID + " INTEGER UNIQUE NOT NULL, " +
                        COL_GOAL_WEIGHT + " REAL NOT NULL, " +
                        "FOREIGN KEY(" + COL_GOAL_USER_ID + ") REFERENCES " +
                        TABLE_USERS + "(" + COL_USER_ID + ") ON DELETE CASCADE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 4) {
            // SQLiteOpenHelper wraps this upgrade in a transaction. Keep complete
            // legacy tables so entries with unknown ownership remain recoverable.
            db.execSQL("ALTER TABLE goal RENAME TO goal_legacy_v4");
            db.execSQL("ALTER TABLE weights RENAME TO weights_legacy_v4");
            db.execSQL("ALTER TABLE users RENAME TO users_legacy_v4");
            onCreate(db);
            db.execSQL("INSERT INTO users(id, username, password) " +
                    "SELECT id, username, password FROM users_legacy_v4 " +
                    "WHERE username IS NOT NULL AND password IS NOT NULL");
            if (hasColumn(db, "weights_legacy_v4", "user_id")) {
                db.execSQL("INSERT INTO weights(id, user_id, weight, date) " +
                        "SELECT w.id, w.user_id, w.weight, w.date " +
                        "FROM weights_legacy_v4 w JOIN users u ON u.id = w.user_id " +
                        "WHERE w.weight IS NOT NULL AND w.date IS NOT NULL");
            }
            if (hasColumn(db, "goal_legacy_v4", "user_id")) {
                db.execSQL("INSERT INTO goal(user_id, goal_weight) " +
                        "SELECT g.user_id, g.goal_weight FROM goal_legacy_v4 g " +
                        "JOIN users u ON u.id = g.user_id " +
                        "WHERE g.goal_weight IS NOT NULL AND g.id = " +
                        "(SELECT MAX(g2.id) FROM goal_legacy_v4 g2 " +
                        "WHERE g2.user_id = g.user_id AND g2.goal_weight IS NOT NULL)");
            }
            // Global goals and ownerless weights stay in the legacy tables;
            // assigning another person's health records would be incorrect.
        }
    }

    private boolean hasColumn(SQLiteDatabase db, String table, String column) {
        try (Cursor cursor = db.rawQuery("PRAGMA table_info(" + table + ")", null)) {
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            while (cursor.moveToNext()) {
                if (column.equals(cursor.getString(nameIndex))) return true;
            }
        }
        return false;
    }

    public boolean insertUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);

        return result != -1;
    }

    public int getUserId(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COL_USER_ID},
                COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?",
                new String[]{username, password},
                null,
                null,
                null
        );

        int userId = -1;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COL_USER_ID)
            );
        }

        cursor.close();

        return userId;
    }

    public boolean checkUser(String username, String password) {
        return getUserId(username, password) != -1;
    }

    public boolean userExists(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COL_USER_ID},
                COL_USERNAME + " = ?",
                new String[]{username},
                null,
                null,
                null
        );

        boolean exists = cursor.moveToFirst();

        cursor.close();

        return exists;
    }

    public boolean insertWeight(int userId, double weight, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT_USER_ID, userId);
        values.put(COL_WEIGHT_VALUE, weight);
        values.put(COL_WEIGHT_DATE, date);

        long result = db.insert(TABLE_WEIGHTS, null, values);

        return result != -1;
    }

    public Cursor getWeightsForUser(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(
                TABLE_WEIGHTS,
                null,
                COL_WEIGHT_USER_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                COL_WEIGHT_ID + " DESC"
        );
    }

    public boolean updateWeight(int weightId, int userId, double weight, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT_VALUE, weight);
        values.put(COL_WEIGHT_DATE, date);

        int rowsUpdated = db.update(
                TABLE_WEIGHTS,
                values,
                COL_WEIGHT_ID + " = ? AND " + COL_WEIGHT_USER_ID + " = ?",
                new String[]{
                        String.valueOf(weightId),
                        String.valueOf(userId)
                }
        );

        return rowsUpdated > 0;
    }

    public boolean deleteWeight(int weightId, int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_WEIGHTS,
                COL_WEIGHT_ID + " = ? AND " + COL_WEIGHT_USER_ID + " = ?",
                new String[]{
                        String.valueOf(weightId),
                        String.valueOf(userId)
                }
        );

        return rowsDeleted > 0;
    }

    public boolean saveGoalWeight(int userId, double goalWeight) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_GOAL_WEIGHT, goalWeight);

        int updated = db.update(
                TABLE_GOAL,
                values,
                COL_GOAL_USER_ID + " = ?",
                new String[]{String.valueOf(userId)}
        );

        if (updated > 0) {
            return true;
        }

        values.put(COL_GOAL_USER_ID, userId);

        long result = db.insert(TABLE_GOAL, null, values);

        return result != -1;
    }

    public double getGoalWeight(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_GOAL,
                new String[]{COL_GOAL_WEIGHT},
                COL_GOAL_USER_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
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
