package com.offer.pojo;

import java.util.Date;

public class PfoSignUp {
    private Long signUpId;

    private Long userId;

    private Long recruitmentId;

    private Date deliverTime;

    /**
     * 报名表关联用户
     */
    private PfoUser user;

    /**
     * 报名表关联的招聘信息
     */
    private PfoRecruitment recruitment;

    public PfoRecruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(PfoRecruitment recruitment) {
        this.recruitment = recruitment;
    }

    public PfoUser getUser() {
        return user;
    }

    public void setUser(PfoUser user) {
        this.user = user;
    }

    public Long getSignUpId() {
        return signUpId;
    }

    public void setSignUpId(Long signUpId) {
        this.signUpId = signUpId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(Long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }
}