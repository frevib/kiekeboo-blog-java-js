package com.kiekeboo.app.model;

import java.util.Date;

public class UserDataModel {

    private int userId;
    private Date loginDate;
    private String salt;
    private int roleId;
    private String encryptionKeyJWT;
    private String username;
    private String password;
    private String email;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
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
