package com.offer.pojo;

import java.util.Date;

public class PfoPaper {
    private Long paperId;

    private String paperName;

    private Date createTime;

    private Date openTime;

    private Date closeTime;

    private String questionListId;

    private String paperDescription;

    private String paperImg;

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getQuestionListId() {
        return questionListId;
    }

    public void setQuestionListId(String questionListId) {
        this.questionListId = questionListId == null ? null : questionListId.trim();
    }

    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription == null ? null : paperDescription.trim();
    }

    public String getPaperImg() {
        return paperImg;
    }

    public void setPaperImg(String paperImg) {
        this.paperImg = paperImg == null ? null : paperImg.trim();
    }
}