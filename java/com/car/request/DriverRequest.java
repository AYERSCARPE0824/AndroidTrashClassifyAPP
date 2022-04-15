package com.car.request;

import java.io.Serializable;

public class DriverRequest implements Serializable {
    public static final int LOGIN_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    private static final long serialVersionUID = -9066775029985010959L;
    private int requestType;
    private String username;
    private String password;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
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
}
