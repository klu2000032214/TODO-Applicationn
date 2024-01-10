package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class update extends AppCompatActivity {

    private TodoDatabaseHelper dbHelper;
    private EditText updateIdEditText;
    private EditText updateTitleEditText;
    private EditText updateDescriptionEditText;
    private EditText updateDueDateEditText;
    private Spinner updatePrioritySpinner;
    private EditText updateCategoryEditText;
    private Spinner updateStatusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new TodoDatabaseHelper(this);

        updateIdEditText = findViewById(R.id.updateIdEditText);
        updateTitleEditText = findViewById(R.id.titleEditText);
        updateDescriptionEditText = findViewById(R.id.descriptionEditText);
        updateDueDateEditText = findViewById(R.id.dueDateEditText);
        updatePrioritySpinner = findViewById(R.id.prioritySpinner);
        updateCategoryEditText = findViewById(R.id.categoryEditText);
        updateStatusSpinner = findViewById(R.id.statusSpinner);

        Button updateButton = findViewById(R.id.update_Button);
        Button backButton = findViewById(R.id.back_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTxt = updateIdEditText.getText().toString();
                String titleTxt = updateTitleEditText.getText().toString();
                String descriptionTxt = updateDescriptionEditText.getText().toString();
                String dueDateTxt = updateDueDateEditText.getText().toString();
                String priorityTxt = updatePrioritySpinner.getSelectedItem().toString();
                String categoryTxt = updateCategoryEditText.getText().toString();
                String statusTxt = updateStatusSpinner.getSelectedItem().toString();

                Boolean checkUpdateData = dbHelper.updateuserdata(idTxt, titleTxt, descriptionTxt, dueDateTxt, priorityTxt, categoryTxt, statusTxt);

                if (checkUpdateData) {
                    Toast.makeText(update.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after updating the task
                } else {
                    Toast.makeText(update.this, "Task not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity when the "Cancel" button is clicked
            }
        });
    }
}
