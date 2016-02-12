package com.kiekeboo.app.model;

import javax.validation.constraints.Pattern;

public class UserRequestModel {

    // Only allow word characters, max length 30
    @Pattern(regexp = "[\\w]{1,30}", message = "Username contains invalid characters, or too many characters.")
    private String username;

    // Only allow word characters and whitespace, max length 50
    @Pattern(regexp = "[\\w\\s]{1,50}", message = "Password contains invalid characters, or too many characters.")
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
