package com.offer.pojo;

import java.util.List;

public class PfoTopic {
    private Integer topicId;

    private String topicName;

    //一对多，一个主题对应多个帖子
    private List<PfoPost> postList;

    public List<PfoPost> getPostList() {
        return postList;
    }

    public void setPostList(List<PfoPost> postList) {
        this.postList = postList;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }
}