package com.example.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;

import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_STATUS = "status";

    // SQL statement to create the tasks table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TASKS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_DUE_DATE + " TEXT, " +
                    COLUMN_PRIORITY + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_STATUS + " TEXT);";

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tasks table
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table and recreate it if the database version is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public boolean updateuserdata(String id, String title, String description, String due_date, String priority, String category, String status) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("due_date", due_date);
        contentValues.put("priority", priority);
        contentValues.put("category", category);
        contentValues.put("status", status);

        // Use both id and title in the WHERE clause
        String whereClause = "id=?";
        String[] whereArgs = new String[]{id};

        Cursor cursor = DB.rawQuery("select * from tasks where id=?", new String[]{id});

        if (cursor.getCount() > 0) {
            // Update the task based on both id and title
            long result = DB.update("tasks", contentValues, whereClause, whereArgs);

            // Check if the update was successful
            return result != -1;
        } else {
            return false; // Task with the given ID and title not found
        }
    }

    public boolean deleteTaskData(String id, String title) {
        SQLiteDatabase DB = this.getWritableDatabase();

        // Define the WHERE clause with placeholders
        String whereClause = "id=? AND title=?";
        // Provide the values for the placeholders
        String[] whereArgs = new String[]{id, title};

        // Use the delete method with the correct parameters
        long results = DB.delete("tasks", whereClause, whereArgs);

        // Check the results of the delete operation
        if (results != -1) {
            return true; // Successfully deleted
        } else {
            return false; // Failed to delete
        }
    }

    public boolean deleteTaskDataAll() {
        SQLiteDatabase DB = this.getWritableDatabase();

        // Provide the table name and set whereClause to null for deleting all records
        long results = DB.delete("tasks", null, null);

        // Check the results of the delete operation
        if (results != -1) {
            return true; // Successfully deleted
        } else {
            return false; // Failed to delete
        }
    }


    public Cursor getData(){
            SQLiteDatabase DB=this.getWritableDatabase();
            Cursor cursor=DB.rawQuery("select * from tasks",null);
            return cursor;
        }
    }
