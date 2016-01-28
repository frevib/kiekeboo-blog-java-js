package com.kiekeboo.app.model;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserDataModel {

    private int userId;
    private Date loginDate;
    private String salt;
    private int roleId;
    private String encryptionKeyJWT;

//    don't need to validate again in for the transport (database) data
    // Only allow word characters, max length 30
    @Pattern(regexp = "[\\w]{1,30}", message = "Username contains invalid characters")
    private String username;

    // Only allow word characters, max length 50
    @Pattern(regexp = "[\\w\\s]{1,50}", message = "Password contains invalid characters")
    private String password;

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
