package com.example.quizapp.views.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.quizapp.R;

import com.example.quizapp.model.QuestionAnswerModel;

import com.example.quizapp.model.QuestionModel;
import com.example.quizapp.model.QuizListModel;
import com.example.quizapp.networks.APIService;
import com.example.quizapp.networks.ApiUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizListActivity extends AppCompatActivity {

    private static final String TAG = "Quiz List";
    private List<QuestionAnswerModel> quizList;
    private List<QuestionModel> questionList;
    private int seconds;
    int indexCurrentQuestion = 0;
    int currentScore = 0, questionAttempted = 1, currentPos;

    private TextView questionView, attemptedView, scoreTextView, totalScoreTextView;

    private RadioGroup radioGroup;
    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;
    private RadioButton radioButtonThree;
    private RadioButton radioButtonFour;
    private Button buttonPrevious;
    private Button buttonNext;
    Random random;
    Button viewResults;
    private TextView textTime;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_quiz_list);
        attemptedView = findViewById(R.id.attemptedQuestion);
        questionView = findViewById(R.id.celebrityQuestion);
        radioGroup = findViewById(R.id.celebrityOption);
        radioButtonOne = findViewById(R.id.radioButtonOne);
        radioButtonTwo = findViewById(R.id.radioButtonTwo);
        radioButtonThree = findViewById(R.id.radioButtonThree);
        radioButtonFour = findViewById(R.id.radioButtonFour);
        textTime = findViewById(R.id.textTime);

        radioButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                if (questionList.get(indexCurrentQuestion).correctAnswer.equals(radioButtonOne.getText().toString())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(questionList.size());
                //questionList.get(indexCurrentQuestion).correctAnswer = "1";
            }
        });
        radioButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                if (questionList.get(indexCurrentQuestion).correctAnswer.equals(radioButtonTwo.getText().toString())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(questionList.size());
                //questionList.get(indexCurrentQuestion).correctAnswer = "2";
            }
        });

        radioButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                if (questionList.get(indexCurrentQuestion).correctAnswer.equals(radioButtonThree.getText().toString())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(questionList.size());
                //questionList.get(indexCurrentQuestion).correctAnswer = "3";
            }
        });

        radioButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                if (questionList.get(indexCurrentQuestion).correctAnswer.equals(radioButtonFour.getText().toString())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(questionList.size());
                //questionList.get(indexCurrentQuestion).correctAnswer = "4";
            }
        });

        // Define button views
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);

        // Access intent interface and get variables
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d(TAG, "onCreate: " + id);
        seconds = intent.getIntExtra("seconds", 50);
        APIService mApiService = ApiUtils.getApiService();
        mApiService.getQuestionList(id).enqueue(new Callback<List<QuizListModel>>() {
            @Override
            public void onResponse(Call<List<QuizListModel>> call, Response<List<QuizListModel>> response) {
                questionList = response.body().get(0).getQuestionlist();
                Log.d(TAG, "onResponse: " + questionList.get(0).getQuestion());
                random = new Random();
                currentPos = random.nextInt(questionList.size());
                Log.d(TAG, "onResponse: " + currentPos);
                QuestionModel currentQuestion = questionList.get(currentPos);
                Log.d(TAG, "Current Question: " + currentQuestion);
                currentQuestionView(currentQuestion);
            }

            @Override
            public void onFailure(Call<List<QuizListModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                System.out.println(t.fillInStackTrace());
            }
        });
        buttonPrevious.setEnabled(false); // Disable previous button when current index is 0

        // See function
        startTimer();
        // When user submit quiz, stop time and start Solution Activity
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                showBottomSheet(currentScore);

            }
        });
    }

    public void showBottomSheet(int currentScore) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizListActivity.this);
        View layoutInflater = getLayoutInflater().inflate(R.layout.bottom_score, null);
        scoreTextView = layoutInflater.findViewById(R.id.scoreTextView);
        totalScoreTextView = layoutInflater.findViewById(R.id.scoreTotalTextView);
        viewResults = layoutInflater.findViewById(R.id.viewResultsButton);
        Log.d(TAG, "Score: " + currentScore);
        scoreTextView.setText(String.valueOf(currentScore));
        totalScoreTextView.setText(String.valueOf(questionList.size()));
        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuizListActivity.this, SolutionActivity.class);
                i.putExtra("score", currentScore);
                Log.d(TAG, "Send score: "+currentScore);
                // Change List to ArrayList to accommodate subList

                ArrayList<QuestionModel> list = new ArrayList<>(questionList);
                i.putExtra("quizList", list);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(layoutInflater);
        bottomSheetDialog.show();
    }

    // Start countdown. OnFinish, start Solution Activity
    public void startTimer() {
        textTime.setText(String.valueOf(seconds));
        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(QuizListActivity.this, SolutionActivity.class);
                i.putExtra("score", currentScore);
                ArrayList<QuestionModel> list = new ArrayList<>(questionList);
                i.putExtra("quizList", list);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        }.start();
    }

    // Cancel timer to prevent countDown in background
    // If not defined, Solution Activity will start even when user goes back to
    // Main Activity because Quiz Activity doesn't get destroyed instantly
    public void stopTimer() {
        countDownTimer.cancel();
    }

    // Pre-define new views before setting previous question as current question, for index < 0
    public void onButtonPrevious(View view) {
        if (indexCurrentQuestion != 0) {
            indexCurrentQuestion--;
            if (indexCurrentQuestion == 0) buttonPrevious.setEnabled(false);
            if (indexCurrentQuestion != (questionList.size() - 1)) buttonNext.setEnabled(true);
            QuestionModel currentQuestion = questionList.get(indexCurrentQuestion);
            currentQuestionView(currentQuestion);
            radioGroup = findViewById(R.id.celebrityOption);
            /*if (currentQuestion.correctAnswer.equals("")) radioGroup.clearCheck();
            else {
                switch (currentQuestion.correctAnswer) {
                    case "1": {
                        radioGroup.check(R.id.radioButtonOne);
                        break;
                    }
                    case "2": {
                        radioGroup.check(R.id.radioButtonTwo);
                        break;
                    }
                    case "3": {
                        radioGroup.check(R.id.radioButtonThree);
                        break;
                    }
                    case "4": {
                        radioGroup.check(R.id.radioButtonFour);
                        break;
                    }
                }
            }*/
        }
    }

    // Pre-define new views before setting next question as current question, for index > list.size()
    public void onButtonNext(View view) {
        if (indexCurrentQuestion != (questionList.size() - 1)) {
            indexCurrentQuestion++;
            if (indexCurrentQuestion == (questionList.size() - 1)) buttonNext.setEnabled(false);
            if (indexCurrentQuestion != 0) buttonPrevious.setEnabled(true);
            QuestionModel currentQuestion = questionList.get(indexCurrentQuestion);
            currentQuestionView(currentQuestion);
            radioGroup = findViewById(R.id.celebrityOption);
            /*if (currentQuestion.correctAnswer.equals("")) radioGroup.clearCheck();
            else {
                switch (currentQuestion.correctAnswer) {
                    case "1": {
                        radioGroup.check(R.id.radioButtonOne);
                        break;
                    }
                    case "2": {
                        radioGroup.check(R.id.radioButtonTwo);
                        break;
                    }
                    case "3": {
                        radioGroup.check(R.id.radioButtonThree);
                        break;
                    }
                    case "4": {
                        radioGroup.check(R.id.radioButtonFour);
                        break;
                    }
                }
            }*/
        }
    }

    public void currentQuestionView(QuestionModel currentQuestion) {
        Log.d(TAG, "currentQuestionView: " + currentQuestion);
        attemptedView.setText("Question Attempted " + questionAttempted + '/' + questionList.size());
        questionView.setText(currentQuestion.question);
        radioButtonOne.setText(currentQuestion.correctAnswer);
        radioButtonTwo.setText(currentQuestion.incorrectAnswers.get(0));
        radioButtonThree.setText(currentQuestion.incorrectAnswers.get(1));
        radioButtonFour.setText(currentQuestion.incorrectAnswers.get(2));
    }
}

