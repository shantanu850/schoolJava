package com.biswatex.school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ExamsList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue rq;
    List<ExamModel> examModelList;
    String request_url = "http://biswatex.com/api/examsfetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinations);
        rq = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerviewexaminations);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        examModelList = new ArrayList<>();
        sendRequest();
    }
    public void sendRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){
                for(int i = 0; i < response.length(); i++){
                    ExamModel examModel = new ExamModel();
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        examModel.setExamid(jsonObject.getString("question_set_id"));
                        examModel.setExamName(jsonObject.getString("teacher_id"));
                        examModel.setExamClass(jsonObject.getString("class"));
                        examModel.setexamDuration(jsonObject.getString("start_time"));
                        examModel.setexamFullMarks(jsonObject.getString("full_marks"));
                        examModel.setexamPassMarks(jsonObject.getString("pass_marks"));
                        examModel.setexamSection(jsonObject.getString("sec"));
                        examModel.setexamSubject(jsonObject.getString("sub"));
                        examModel.setexamType(jsonObject.getString("type"));
                        examModel.setexamResultOut(jsonObject.getString("end_time"));
                        examModel.setExamTeacher(jsonObject.getString("teacher_name"));
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                    examModelList.add(examModel);
                }
                mAdapter = new CustomRecycleAdapter(ExamsList.this, examModelList,new  CustomRecycleAdapter.TakeExamListner(){
                    @Override
                    public void TakeExamOnClick(View v, int position) {
                        Intent intent = new Intent(ExamsList.this, ActivityViewExam.class);
                        intent.putExtra("exam_name", examModelList.get(position).getExamId());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", error.toString());
            }
        });
        rq.add(jsonArrayRequest);
    }
}