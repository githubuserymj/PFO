package com.offer.pojo;

public class PfoRecommend {
    private Integer recommendId;

    private String paperIdList;

    private String postIdList;

    private String recruitmentIdList;

    private String newsIdList;

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public String getPaperIdList() {
        return paperIdList;
    }

    public void setPaperIdList(String paperIdList) {
        this.paperIdList = paperIdList == null ? null : paperIdList.trim();
    }

    public String getPostIdList() {
        return postIdList;
    }

    public void setPostIdList(String postIdList) {
        this.postIdList = postIdList == null ? null : postIdList.trim();
    }

    public String getRecruitmentIdList() {
        return recruitmentIdList;
    }

    public void setRecruitmentIdList(String recruitmentIdList) {
        this.recruitmentIdList = recruitmentIdList == null ? null : recruitmentIdList.trim();
    }

    public String getNewsIdList() {
        return newsIdList;
    }

    public void setNewsIdList(String newsIdList) {
        this.newsIdList = newsIdList == null ? null : newsIdList.trim();
    }
}