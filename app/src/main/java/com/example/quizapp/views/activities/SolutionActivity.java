package com.example.quizapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.SolutionAdapter;
import com.example.quizapp.model.QuestionAnswerModel;
import com.example.quizapp.model.QuestionModel;
import com.example.quizapp.model.QuizListModel;

import java.util.List;

public class SolutionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        // Interface instance to get values from QuizActivity
        int scoreValue = getIntent().getIntExtra("score", 0);
        List<QuestionModel> quizList = (List<QuestionModel>) getIntent().getSerializableExtra("quizList");

        // Set view and display scoreValue
        TextView scoreView = findViewById(R.id.scoreTextView);
        scoreView.setText(String.valueOf(scoreValue));

        // Set score out-of view
        TextView scoreTotalView = findViewById(R.id.scoreTotalTextView);
        scoreTotalView.setText(String.valueOf(quizList.size()));

        // See function
        displayWellDone(scoreValue);

        // RecycleView definitions
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SolutionAdapter solutionAdapter = new SolutionAdapter(quizList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(solutionAdapter);
    }

    // Function to display well done image if user gets all correct | also settings for total value
    public void displayWellDone(int score) {

        // Set view for well done image
        ImageView imageView = findViewById(R.id.wellDoneImage);
        imageView.setVisibility(View.INVISIBLE); // set image invisible

        // display well done image if user gets all correct
        if (score == 5) imageView.setVisibility(View.VISIBLE);
    }
    public void startQuiz(View view) {
        Intent i = new Intent(SolutionActivity.this, Landing_Page_Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}
