package com.kiekeboo.app.model;

import java.util.Date;

public class UserDataModel extends UserModel {

    private Date loginDate;

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public UserDataModel mapRequestToDataModel(UserRequestModel userRequestModel) {
        this.setUsername(userRequestModel.getUsername());
        this.setPassword(userRequestModel.getPassword());
        setLoginDate(new Date());
        return this;
    }

}
