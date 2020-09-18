package com.offer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "item",type = "item", shards = 4, replicas = 0)
public class PfoPost {
    @Id
    private Long postId;

    @Field(type = FieldType.Keyword)
    private Long userId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String postTitle;

    @Field(type = FieldType.Keyword)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deliverTime;

    @Field(type = FieldType.Keyword)
    private Boolean postStatus;

    @Field(type = FieldType.Keyword)
    private Integer topicId;

    @Field(type = FieldType.Keyword)
    private Integer viewCount;

    @Field(type = FieldType.Keyword)
    private Integer commentCount;

    @Field(type = FieldType.Keyword)
    private Integer oCoin;

    @Field(type = FieldType.Keyword)
    private String originUrl;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String postBody;

    @Field(type = FieldType.Keyword)
    private Integer favorCount;

    @Override
    public String toString() {
        return "PfoPost{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", postTitle='" + postTitle + '\'' +
                ", deliverTime=" + deliverTime +
                ", postStatus=" + postStatus +
                ", topicId=" + topicId +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", oCoin=" + oCoin +
                ", originUrl='" + originUrl + '\'' +
                ", postBody='" + postBody + '\'' +
                ", favorCount=" + favorCount +
                '}';
    }

    public PfoPost(Long postId, Long userId, String postTitle, Date deliverTime, Boolean postStatus, Integer topicId, Integer viewCount, Integer commentCount, Integer oCoin, String originUrl, String postBody, Integer favorCount) {
        this.postId = postId;
        this.userId = userId;
        this.postTitle = postTitle;
        this.deliverTime = deliverTime;
        this.postStatus = postStatus;
        this.topicId = topicId;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.oCoin = oCoin;
        this.originUrl = originUrl;
        this.postBody = postBody;
        this.favorCount = favorCount;
    }

    public PfoPost() {
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Boolean getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getoCoin() {
        return oCoin;
    }

    public void setoCoin(Integer oCoin) {
        this.oCoin = oCoin;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl == null ? null : originUrl.trim();
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody == null ? null : postBody.trim();
    }
}