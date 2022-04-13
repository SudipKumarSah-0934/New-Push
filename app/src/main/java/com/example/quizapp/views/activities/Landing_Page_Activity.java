package com.example.quizapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.quizapp.R;
import com.example.quizapp.adapter.QuizListAdapter;
import com.example.quizapp.model.QuestionModel;
import com.example.quizapp.model.QuizListModel;
import com.example.quizapp.networks.APIService;
import com.example.quizapp.networks.ApiUtils;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Landing_Page_Activity extends AppCompatActivity {
    private static final String TAG = "Landing Page Check1";
    TextView header;
    ArrayList<QuestionModel>user_array;
    ImageButton profileBtn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    QuizListAdapter.RecyclerViewClickListener listener;
    QuizListAdapter quizListAdapter;
    List<QuizListModel> quizList;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
       // Log.d(TAG, "onCreate: "+quizList);
        header = findViewById(R.id.header_title);
        profileBtn =findViewById(R.id.profileBtn);
        progressBar = findViewById(R.id.progressBar1);
        recyclerView = findViewById(R.id.recyclerView5);
        fetchQuizLists();
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Landing_Page_Activity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

    }

    public void fetchQuizLists() {
        progressBar.setVisibility(View.VISIBLE);
        APIService mApiService = ApiUtils.getApiService();
        mApiService.getQuizList().enqueue(new Callback<List<QuizListModel>>() {
            @Override
            public void onResponse(Call<List<QuizListModel>> call, Response<List<QuizListModel>> response) {
                //Log.d(TAG, "onResponse:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+response.body());
                if (response.isSuccessful() && response.body()!=null) {
                    quizList = (response.body());

                    layoutManager = new LinearLayoutManager(Landing_Page_Activity.this);
                    //Response<List<QuestionModel>> response1;
                    recyclerView.setLayoutManager(layoutManager);
                    quizListAdapter = new QuizListAdapter(Landing_Page_Activity.this,quizList,listener);
                    recyclerView.setAdapter(quizListAdapter);
                    progressBar.setVisibility(View.GONE);
                    //Log.d(TAG, "onResponse: "+quizList);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                    } catch (Exception e) {
                        Log.d("checkLocationerror", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuizListModel>> call, Throwable t) {
                Log.d(TAG, "onFailure:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+t.getLocalizedMessage());
            }
        });
    }

}