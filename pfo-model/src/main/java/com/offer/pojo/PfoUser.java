package com.offer.pojo;


import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class PfoUser {
    private Long userId;

    private String userName;

    private String userPassword;

    private Integer userType;

    private String gender;

    private Date birthday;

    private Integer oCoin;

    private Date regDate;

    private String userPhoto;

    private String userEmail;

    private String userSignature;

    private String userPhone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getoCoin() {
        return oCoin;
    }

    public void setoCoin(Integer oCoin) {
        this.oCoin = oCoin;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature == null ? null : userSignature.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    @Override
    public String toString() {
        return "PfoUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType=" + userType +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", oCoin=" + oCoin +
                ", regDate=" + regDate +
                ", userPhoto='" + userPhoto + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSignature='" + userSignature + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}