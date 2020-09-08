package com.biswatex.school;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TakeExam extends AppCompatActivity {

    String id = "44";
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue rq;
    public static List<QuestionModel> questionModelList;
    String request_url = "http://biswatex.com/api/questionfetch.php";
    static String time = "";
    static  CountDownTimer Counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        Intent intent = getIntent();
        final TextView timer = findViewById(R.id.txt_timer_take_exam);
        rq = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerview_startExam);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        questionModelList = new ArrayList<>();
        sendRequest();
        Counter = new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                timer.setText("done!");
            }
        }.start();
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
                        QuestionModel questionModel = new QuestionModel();
                        try{
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            questionModel.setquestion(jsonObject.getString("question"));
                            questionModel.setMarks(jsonObject.getString("marks"));
                            questionModel.setop1(jsonObject.getString("op_1"));
                            questionModel.setOp2(jsonObject.getString("op_2"));
                            questionModel.setOp3(jsonObject.getString("op_3"));
                            questionModel.setOp4(jsonObject.getString("op_4"));
                            questionModel.setType(jsonObject.getString("type"));
                            questionModel.setans(jsonObject.getString("answer"));
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        questionModelList.add(questionModel);
                    }
                    mAdapter = new QuestionAdapter(TakeExam.this, questionModelList,new  QuestionAdapter.SubmitExamListner(){
                        @Override
                        public void SubmitExamOnClick(View v, int position) {
                            Intent intent = new Intent(TakeExam.this, QuestionView.class);
                            intent.putExtra("index",position);
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
                Log.i("Volley Error : ", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("teacher_exam_id",id);
                return params;
            }
        };
        rq.add(jsonArrayRequest);
    }
}
