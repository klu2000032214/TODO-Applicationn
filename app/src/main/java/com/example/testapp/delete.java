package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {
    private TodoDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dbHelper = new TodoDatabaseHelper(this);

        final EditText title_no = findViewById(R.id.tit_no);
        final EditText title_task = findViewById(R.id.title_task);
        Button delete = findViewById(R.id.del_Button);
        Button cancel = findViewById(R.id.back_button);
        Button del_All=findViewById(R.id.delAllButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tit_no=title_no.getText().toString();
                String title=title_task.getText().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(tit_no)){
                    Toast.makeText(delete.this,"Both fields are require!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkudeletedata = dbHelper.deleteTaskData(tit_no, title);

                    if (checkudeletedata == true) {
                        Toast.makeText(delete.this, "Successfully deleted😊", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(delete.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(delete.this, "Failed to delete😒", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        del_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkudeletedata = dbHelper.deleteTaskDataAll();

                if (checkudeletedata == true) {
                    Toast.makeText(delete.this, "Successfully All Task has deleted😊", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(delete.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(delete.this, "Failed to delete😒", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTaskActivity
                Intent intent = new Intent(delete.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}