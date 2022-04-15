package com.car.result;

import java.io.Serializable;

public class DriverResult implements Serializable {
    private static final long serialVersionUID = 704187858634866220L;
    private String username;
    private boolean res;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
}
