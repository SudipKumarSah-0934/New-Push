package com.example.quizapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuizListModel implements Serializable {

    @SerializedName("Quizname")
    @Expose
    private String quizname;
    @SerializedName("Numberofquestion")
    @Expose
    private Integer numberofquestion;
    @SerializedName("Time")
    @Expose
    private Integer time;
    @SerializedName("Marks")
    @Expose
    private Integer marks;
    @SerializedName("Ranks")
    @Expose
    private String ranks;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("expanded")
    @Expose
    private Boolean expanded=false;
    @SerializedName("Questionlist")
    @Expose
    private List<QuestionModel> questionlist = null;

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public Integer getNumberofquestion() {
        return numberofquestion;
    }

    public void setNumberofquestion(Integer numberofquestion) {
        this.numberofquestion = numberofquestion;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public boolean isExpanded() {
        return expanded;
    }
    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
    public List<QuestionModel> getQuestionlist() {
        return questionlist;
    }

    public void setQuestionlist(List<QuestionModel> questionlist) {
        this.questionlist = questionlist;
    }

    @Override
    public String toString() {
        return "QuizListModel{" +
                "quizname='" + quizname + '\'' +
                ", numberofquestion=" + numberofquestion +
                ", time=" + time +
                ", marks=" + marks +
                ", ranks='" + ranks + '\'' +
                ", id='" + id + '\'' +
                ", expanded=" + expanded +
                ", questionlist=" + questionlist +
                '}';
    }
}