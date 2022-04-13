package com.example.quizapp.model;

import java.io.Serializable;
public class QuestionAnswerModel implements Serializable {
    public String question;
    public String one;
    public String two;
    public String three;
    public String four;
    public int correctAnswer;
    public int userAnswer;

    QuestionAnswerModel(String question, String one, String two,
                        String three, String four, int correctAnswer, int userAnswer) {
        this.question = question;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }
}