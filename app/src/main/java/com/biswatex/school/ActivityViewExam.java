package com.biswatex.school;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityViewExam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__exam);
        Intent intent = getIntent();
        String str = intent.getStringExtra("exam_name");
        TextView textView = findViewById(R.id.activity_view_exams_exam_name);
        textView.setText(str);
    }
}