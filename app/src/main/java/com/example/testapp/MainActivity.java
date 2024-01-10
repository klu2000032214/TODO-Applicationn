package com.example.testapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TodoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the "Add Task" button by its ID
        Button addTaskButton = findViewById(R.id.addTaskButton);
        Button view=findViewById(R.id.btnview);
        Button delete=findViewById(R.id.delButton);
        Button update=findViewById(R.id.update_btn);
        dbHelper = new TodoDatabaseHelper(this);

        // Set a click listener for the button
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTaskActivity
                Intent intent = new Intent(MainActivity.this, add_task.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTaskActivity
                Intent intent = new Intent(MainActivity.this, delete.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTaskActivity
                Intent intent = new Intent(MainActivity.this, update.class);
                startActivity(intent);
            }
        });

        // ============ View data codes ==================

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=dbHelper.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"no data",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Task No :"+res.getString(0)+"\n");
                    buffer.append("Title :"+res.getString(1)+"\n");
                    buffer.append("Description :"+res.getString(2)+"\n");
                    buffer.append("Due Date :"+res.getString(3)+"\n");
                    buffer.append("Priority :"+res.getString(4)+"\n");
                    buffer.append("Category :"+res.getString(5)+"\n");
                    buffer.append("Status :"+res.getString(6)+"\n ------------------------ \n");

                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Total Task!");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}