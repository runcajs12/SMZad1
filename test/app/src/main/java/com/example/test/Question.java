package com.example.test;

public class Question {
    public int questionId;
    public boolean trueAnswer;

    public int getQuestionId(){
        return questionId;
    }
    public boolean gettrueAnswer(){
        return trueAnswer;
    }

    public Question(int questionId, boolean trueAnswer) {
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }
}
