package com.kiekeboo.app.model;

import javax.validation.constraints.Pattern;

public abstract class UserModel {

    // Only allow word characters, max length 30
    @Pattern(regexp = "[\\w\\s]{1,30}")
    private String username;

    // Only allow word characters, max length 50
    @Pattern(regexp = "[\\w\\s]{1,50}")
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


}
