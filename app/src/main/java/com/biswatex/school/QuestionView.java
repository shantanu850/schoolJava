package com.biswatex.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.List;

public class QuestionView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        List<QuestionModel> pu = TakeExam.questionModelList;
        RadioButton op1 = findViewById(R.id.radio1_assessment);
        op1.setText(pu.get(index).getop1());
        RadioButton op2 = findViewById(R.id.radio2_assessment);
        op2.setText(pu.get(index).getop1());
        RadioButton op3 = findViewById(R.id.radio3_assessment);
        op3.setText(pu.get(index).getop1());
        RadioButton op4 = findViewById(R.id.radio4_assessment);
        op4.setText(pu.get(index).getop1());
        TextView question = findViewById(R.id.quetion_assessment);
        question.setText(pu.get(index).getquestion());
    }
}