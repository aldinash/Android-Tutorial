package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnCourses, btnAssignments, btnExams, btnLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnCourses = (Button) findViewById(R.id.mainCourses);
        btnExams = (Button) findViewById(R.id.mainExams);
        btnAssignments = (Button) findViewById(R.id.mainAssignments);
        btnLists = (Button) findViewById(R.id.mainToDoLists);
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(WelcomeActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });
        btnAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(WelcomeActivity.this, AssignmentActivity.class);
                startActivity(intent);
            }
        });
        btnLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(WelcomeActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });
    }
}