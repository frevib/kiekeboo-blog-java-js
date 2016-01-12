package com.kiekeboo.app.model;

public class JsonWebToken {

    private static String name = "jwt";
    private String value;

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
