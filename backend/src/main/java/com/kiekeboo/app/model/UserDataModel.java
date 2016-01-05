package com.kiekeboo.app.model;

import java.util.Date;

public class UserDataModel extends UserModel {

    private int userId;
    private Date loginDate;
    private String salt;
    private int role_id;
    private String encryptionKeyJWT;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncryptionKeyJWT() {
        return encryptionKeyJWT;
    }

    public void setEncryptionKeyJWT(String encryptionKeyJWT) {
        this.encryptionKeyJWT = encryptionKeyJWT;
    }

    public UserDataModel mapRequestToDataModel(UserRequestModel userRequestModel) {
        this.setUsername(userRequestModel.getUsername());
        this.setPassword(userRequestModel.getPassword());
        setLoginDate(new Date());
        return this;
    }
}
