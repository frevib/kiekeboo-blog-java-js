package com.kiekeboo.app.model;

public class JsonWebToken {

    private String name = "jwt";
    private String tokenValue;

    public String getName() {
        return name;
    }
    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
}
