package com.biswatex.school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
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
    List<PersonUtils> personUtilsList;
    String request_url = "http://192.168.43.52/server/examsfetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_list);
        rq = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        personUtilsList = new ArrayList<>();
        sendRequest();
    }
    public void sendRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){
                for(int i = 0; i < response.length(); i++){
                    PersonUtils personUtils = new PersonUtils();
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        personUtils.setPersonFirstName(jsonObject.getString("duration"));
                        personUtils.setPersonLastName(jsonObject.getString("duration"));
                        personUtils.setJobProfile(jsonObject.getString("duration"));
                        Log.i("working","bbbbbbb");
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                    personUtilsList.add(personUtils);
                }
                mAdapter = new CustomRecycleAdapter(ExamsList.this, personUtilsList);
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