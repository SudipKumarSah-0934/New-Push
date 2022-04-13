package com.example.quizapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quizapp.R;
import com.example.quizapp.model.QuestionAnswerModel;
import com.example.quizapp.model.QuestionModel;

import java.util.List;
import java.util.Random;

public class SolutionAdapter extends RecyclerView.Adapter {
    private static final String TAG = "Solution Adapter";
    private List<QuestionModel> quizList;
    Random random;
    private Context context;

    // Constructor to initialize all arrayList
    public SolutionAdapter(List<QuestionModel> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    // Build view layout and call ViewHolder, QuizHolder class
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutInflater = LayoutInflater.from(context).
                inflate(R.layout.solution, viewGroup, false);

        return new RecyclerView.ViewHolder(layoutInflater) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        // Define recycler views
        TextView viewQuestion = viewHolder.itemView.findViewById(R.id.celebrityQuestion);
        RadioGroup radioGroup = viewHolder.itemView.findViewById(R.id.celebrityOption);
        RadioButton radioButtonOne = viewHolder.itemView.findViewById(R.id.radioButtonOne);
        RadioButton radioButtonTwo = viewHolder.itemView.findViewById(R.id.radioButtonTwo);
        RadioButton radioButtonThree = viewHolder.itemView.findViewById(R.id.radioButtonThree);
        RadioButton radioButtonFour = viewHolder.itemView.findViewById(R.id.radioButtonFour);

        viewHolder.itemView.findViewById(R.id.horizontalDivider);


        // Format recycler view content
        if(!quizList.isEmpty()) {
            QuestionModel quiz = quizList.get(position);
            viewQuestion.setText(String.format("%s. %s", position + 1, quiz.question));
            // Glide.with(imageView.getContext()).load(quiz.imageUrl).into(imageView);
            radioButtonOne.setText(quiz.incorrectAnswers.get(0));
            radioButtonTwo.setText(quiz.correctAnswer);
            radioButtonThree.setText(quiz.incorrectAnswers.get(1));
            radioButtonFour.setText(quiz.incorrectAnswers.get(2));
            Log.d(TAG, "onBindViewHolder!!!!: " + radioButtonOne.getText().toString());
            Log.d(TAG, "onBindViewHolder:!!!!! " + quiz.correctAnswer);


            /* First, determine if userAnswer is the same as correctAnswer, IF YES, mark it
             * green and set it checked. ELSE, if user didn't select anything clearCheck() else if
             * userAnswer is wrong, mark userAnswer red, locate
             * correctAnswer and mark it green.
             */


                /*if(quiz.correctAnswer.equals(radioButtonOne.getText().toString())) {
                    radioButtonOne.setChecked(true);
                    radioButtonOne.setTextColor(Color.parseColor("#FF0BA512"));
                }else {
                    radioButtonOne.setChecked(true);
                    radioButtonOne.setTextColor(Color.RED);
                }
            if(quiz.correctAnswer.equals(radioButtonTwo.getText().toString())) {
                radioButtonTwo.setChecked(true);
                radioButtonTwo.setTextColor(Color.parseColor("#FF0BA512"));
            }else {
                radioButtonTwo.setChecked(true);
                radioButtonTwo.setTextColor(Color.RED);
            }*/

            if (quiz.correctAnswer.equals("")) {
                if (radioButtonOne.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonOne.setChecked(true);
                    radioButtonOne.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (radioButtonTwo.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonTwo.setChecked(true);
                    radioButtonTwo.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (radioButtonThree.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonThree.setChecked(true);
                    radioButtonThree.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (radioButtonFour.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonFour.setChecked(true);
                    radioButtonFour.setTextColor(Color.parseColor("#FF0BA512"));
                }
            } else {
                if (radioButtonOne.getText().toString().equals(quiz.getCorrectAnswer())) {
                    radioButtonOne.setChecked(true);
                    radioButtonOne.setTextColor(Color.RED);
                }
                if (radioButtonOne.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonOne.setTextColor(Color.parseColor("#FF0BA512"));
                }

                if (radioButtonTwo.getText().toString().equals(quiz.getIncorrectAnswers().toString())) {
                    radioButtonTwo.setChecked(true);
                    radioButtonTwo.setTextColor(Color.RED);
                }
                if (radioButtonTwo.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonTwo.setTextColor(Color.parseColor("#FF0BA512"));
                }

                if (radioButtonThree.equals(quiz.getIncorrectAnswers().toString())) {
                    radioButtonThree.setChecked(true);
                    radioButtonThree.setTextColor(Color.RED);
                }
                if (radioButtonThree.equals(quiz.correctAnswer)) {
                    radioButtonThree.setTextColor(Color.parseColor("#FF0BA512"));
                }

                if (radioButtonFour.getText().toString().equals(quiz.getIncorrectAnswers().toString())) {
                    radioButtonFour.setChecked(true);
                    radioButtonFour.setTextColor(Color.RED);
                }
                if (radioButtonFour.getText().toString().equals(quiz.correctAnswer)) {
                    radioButtonFour.setTextColor(Color.parseColor("#FF0BA512"));
                }
            }
            //if("0" == quiz.getIncorrectAnswers().toString()) radioGroup.clearCheck();
        }


            // Disable all radioButton to avoid answer misinterpretations
            radioButtonOne.setEnabled(false);
            radioButtonTwo.setEnabled(false);
            radioButtonThree.setEnabled(false);
            radioButtonFour.setEnabled(false);

    }

    // Default ViewHolder methods
    @Override
    public int getItemCount() {
        if (quizList == null) return 0;
        return quizList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
