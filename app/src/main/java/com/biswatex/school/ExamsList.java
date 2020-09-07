package com.biswatex.school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamsList extends AppCompatActivity {

    String cls = "2", sec = "A";
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
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("cls", cls);
//        params.put("sec", sec.toUpperCase());
//
//        JSONObject parameters = new JSONObject(params);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response",response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        ExamModel examModel = new ExamModel();
                        try{
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sec", sec);
                params.put("cls", cls);
                return params;
            }
        };
        rq.add(jsonArrayRequest);
    }
}