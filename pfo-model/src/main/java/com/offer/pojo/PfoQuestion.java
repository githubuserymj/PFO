package com.offer.pojo;

import java.util.List;

public class PfoQuestion {
    private Long questionId;

    private String questionContent;

    private String questionType;

    private String questionSubject;

    private String questionLevel;

    private Integer standardTime;

    private String answer;

    private String answerAnalysis;

    private String answerNote;

    private Integer weigh;

    private List<PfoTag> tags;

    public List<PfoTag> getTags() {
        return tags;
    }

    public void setTags(List<PfoTag> tags) {
        this.tags = tags;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject == null ? null : questionSubject.trim();
    }

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel == null ? null : questionLevel.trim();
    }

    public Integer getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(Integer standardTime) {
        this.standardTime = standardTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnswerAnalysis() {
        return answerAnalysis;
    }

    public void setAnswerAnalysis(String answerAnalysis) {
        this.answerAnalysis = answerAnalysis == null ? null : answerAnalysis.trim();
    }

    public String getAnswerNote() {
        return answerNote;
    }

    public void setAnswerNote(String answerNote) {
        this.answerNote = answerNote == null ? null : answerNote.trim();
    }

    public Integer getWeigh() {
        return weigh;
    }

    public void setWeigh(Integer weigh) {
        this.weigh = weigh;
    }

    @Override
    public String toString() {
        return "PfoQuestion{" +
                "questionId=" + questionId +
                ", questionContent='" + questionContent + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionSubject='" + questionSubject + '\'' +
                ", questionLevel='" + questionLevel + '\'' +
                ", standardTime=" + standardTime +
                ", answer='" + answer + '\'' +
                ", answerAnalysis='" + answerAnalysis + '\'' +
                ", answerNote='" + answerNote + '\'' +
                ", weigh=" + weigh +
                '}';
    }
}