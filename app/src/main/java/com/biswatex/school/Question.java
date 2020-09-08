package com.biswatex.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Question extends AppCompatActivity {
    List<QuestionModel> questionModelList;
    private List<QuestionModel> questionUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        questionModelList = new ArrayList<>();
        QuestionModel pu = questionUtils.get(0);
        RadioButton op1 = findViewById(R.id.radio1_assessment);
        op1.setText(pu.getop1());
        RadioButton op2 = findViewById(R.id.radio2_assessment);
        op2.setText(pu.getop1());
        RadioButton op3 = findViewById(R.id.radio3_assessment);
        op3.setText(pu.getop1());
        RadioButton op4 = findViewById(R.id.radio4_assessment);
        op4.setText(pu.getop1());
        TextView question = findViewById(R.id.quetion_assessment);
        question.setText(pu.getquestion());
    }
}