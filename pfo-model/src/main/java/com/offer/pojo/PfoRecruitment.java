package com.offer.pojo;

import java.util.Date;
import java.util.List;

public class PfoRecruitment {
    private Long recruitmentId;

    private Long companyId;

    private String recruitmentTitle;

    private Date deliverTime;

    private String position;

    private String recruitmentImg;

    private String recruitmentContent;

    private Long paperId;

    /**
     * 招聘信息所属的公司
     */
    private PfoCompany pfoCompany;

    /**
     * 查看招聘信息的报名列表信息 便于统计报名人数
     */
    private List<PfoSignUp> signUpList;

    public List<PfoSignUp> getSignUpList() {
        return signUpList;
    }

    public void setSignUpList(List<PfoSignUp> signUpList) {
        this.signUpList = signUpList;
    }

    public PfoCompany getPfoCompany() {
        return pfoCompany;
    }

    public void setPfoCompany(PfoCompany pfoCompany) {
        this.pfoCompany = pfoCompany;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(Long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRecruitmentTitle() {
        return recruitmentTitle;
    }

    public void setRecruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle == null ? null : recruitmentTitle.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getRecruitmentImg() {
        return recruitmentImg;
    }

    public void setRecruitmentImg(String recruitmentImg) {
        this.recruitmentImg = recruitmentImg == null ? null : recruitmentImg.trim();
    }

    public String getRecruitmentContent() {
        return recruitmentContent;
    }

    public void setRecruitmentContent(String recruitmentContent) {
        this.recruitmentContent = recruitmentContent == null ? null : recruitmentContent.trim();
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
}