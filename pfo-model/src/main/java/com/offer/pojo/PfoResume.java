package com.offer.pojo;

public class PfoResume {
    private Long resumeId;

    private Long userId;

    private String resumeContext;

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getResumeContext() {
        return resumeContext;
    }

    public void setResumeContext(String resumeContext) {
        this.resumeContext = resumeContext == null ? null : resumeContext.trim();
    }

    @Override
    public String toString() {
        return "PfoResume{" +
                "resumeId=" + resumeId +
                ", userId=" + userId +
                ", resumeContext='" + resumeContext + '\'' +
                '}';
    }
}