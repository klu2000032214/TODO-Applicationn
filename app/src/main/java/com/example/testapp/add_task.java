package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class add_task extends AppCompatActivity {
    private TodoDatabaseHelper dbHelper;
    private long taskIdToUpdate = -1; // to store the ID of the task being updated
    private CalendarView dueDateCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dbHelper = new TodoDatabaseHelper(this);

        // Find the "Add Task" button by its ID
        Button addTaskButton = findViewById(R.id.addButton);
        Button cancel = findViewById(R.id.back_button);

        // Find your UI elements
        final EditText titleEditText = findViewById(R.id.titleEditText);
        final EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        final EditText dueDateEditText = findViewById(R.id.dueDateEditText);
        final Spinner prioritySpinner = findViewById(R.id.prioritySpinner);
        final EditText categoryEditText = findViewById(R.id.categoryEditText);
        final Spinner statusSpinner = findViewById(R.id.statusSpinner);

        final Button updateButton = findViewById(R.id.updateButton);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from UI elements
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String dueDate = dueDateEditText.getText().toString();
                String priority = prioritySpinner.getSelectedItem().toString();
                String category = categoryEditText.getText().toString();
                String status = statusSpinner.getSelectedItem().toString();
                // Get values from other UI elements as needed

                // Insert the new task into the database

                // Check if required fields are not empty
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(dueDate)) {
                    showToast("Please fill in all required fields.");
                } else {
                    // Insert the new task into the database
                    insertTask(title, description, dueDate, priority, category, status);
                    finish(); // Close the activity after adding the task
                }
            }
        });

        // Set a click listener for the button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTaskActivity
                Intent intent = new Intent(add_task.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // inserTask method
    private void insertTask(String title, String description, String dueDate,String priority, String category , String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoDatabaseHelper.COLUMN_TITLE, title);
        values.put(TodoDatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(TodoDatabaseHelper.COLUMN_DUE_DATE, dueDate);
        values.put(TodoDatabaseHelper.COLUMN_PRIORITY, priority);
        values.put(TodoDatabaseHelper.COLUMN_CATEGORY, category);
        values.put(TodoDatabaseHelper.COLUMN_STATUS, status);
        // Add other values as needed

        // Insert the new task into the database
        long taskId = db.insert(TodoDatabaseHelper.TABLE_TASKS, null, values);


        // Close the database
        db.close();

        if (taskId != -1) {
            // Task added successfully, show a toast message
            showToast("Task added successfully!");
        } else {
            // Task insertion failed, show an error toast message
            showToast("Failed to add task. Please try again.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}