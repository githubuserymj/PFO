package com.offer.pojo;

import java.util.Date;
import java.util.List;

public class PfoExercised {
    private Long exercisedId;

    private Long questionId;

    private Long userId;

    private Integer state;

    private Date finsihTime;

    private String userAnswer;

    private PfoQuestion question;

    public PfoQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PfoQuestion question) {
        this.question = question;
    }

    public Long getExercisedId() {
        return exercisedId;
    }

    public void setExercisedId(Long exercisedId) {
        this.exercisedId = exercisedId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getFinsihTime() {
        return finsihTime;
    }

    public void setFinsihTime(Date finsihTime) {
        this.finsihTime = finsihTime;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer == null ? null : userAnswer.trim();
    }
}