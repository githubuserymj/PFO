package com.offer.pojo;

import java.util.Date;

public class PfoMessage {
    private Long messageId;

    private Long userId;

    private Date deliverTime;

    private Long targetUserId;

    private String messageType;

    private String messageText;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText == null ? null : messageText.trim();
    }

    @Override
    public String toString() {
        return "PfoMessage{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", deliverTime=" + deliverTime +
                ", targetUserId=" + targetUserId +
                ", messageType='" + messageType + '\'' +
                ", messageText='" + messageText + '\'' +
                '}';
    }
}