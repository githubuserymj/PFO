package com.offer.pojo;

import java.util.Date;

public class PfoExam {
    private Long examId;

    private Long userId;

    private Long paperId;

    private Integer score;

    private Date totalTime;

    private Date startTime;

    private Date finishTime;
    /*
    * 存放试卷映射
    * */
    private PfoPaper pfoPaper;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Date totalTime) {
        this.totalTime = totalTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public PfoPaper getPfoPaper() {
        return pfoPaper;
    }

    public void setPfoPaper(PfoPaper pfoPaper) {
        this.pfoPaper = pfoPaper;
    }

    @Override
    public String toString() {
        return "PfoExam{" +
                "examId=" + examId +
                ", userId=" + userId +
                ", paperId=" + paperId +
                ", score=" + score +
                ", totalTime=" + totalTime +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", pfoPaper=" + pfoPaper +
                '}';
    }
}