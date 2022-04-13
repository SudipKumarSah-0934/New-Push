package com.example.quizapp.networks;

import com.example.quizapp.model.QuestionAnswerModel;
import com.example.quizapp.model.QuestionModel;
import com.example.quizapp.model.QuizListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("/FinalAPI")
    Call<List<QuizListModel>> getQuizList();
    @GET("/question-answer")
    Call<List<QuestionAnswerModel>> getQuesAnsList();
    @GET("/FinalAPI")
    Call<List<QuizListModel>>getQuestionList(
            @Query("id") String id
    );
    @GET("/FinalAPI")
    Call<List<QuestionModel>>getQuestion(
    );
}
