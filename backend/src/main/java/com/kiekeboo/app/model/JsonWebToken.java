package com.kiekeboo.app.model;

import com.kiekeboo.app.model.interfaces.ResponseInterface;

public class JsonWebToken implements ResponseInterface {

    private static String name = "jwt";
    private String value;
    private String message;

    public JsonWebToken() {
    }

    public JsonWebToken(String message){
        this.message = message;
    }

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
