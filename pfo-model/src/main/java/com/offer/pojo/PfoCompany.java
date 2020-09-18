package com.offer.pojo;

public class PfoCompany {
    private Long companyId;

    private Long userId;

    private String companyName;

    private String companyAddress;

    private String companyUrl;

    private String qualification;

    private String logo;

    private String paperListId;

    private String introduce;

    /**
     * 公司关联用户 （企业员工  一对一）
     */
    private PfoUser user;

    public PfoUser getUser() {
        return user;
    }

    public void setUser(PfoUser user) {
        this.user = user;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getPaperListId() {
        return paperListId;
    }

    public void setPaperListId(String paperListId) {
        this.paperListId = paperListId == null ? null : paperListId.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    @Override
    public String toString() {
        return "PfoCompany{" +
                "companyId=" + companyId +
                ", userId=" + userId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", qualification='" + qualification + '\'' +
                ", logo='" + logo + '\'' +
                ", paperListId='" + paperListId + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}