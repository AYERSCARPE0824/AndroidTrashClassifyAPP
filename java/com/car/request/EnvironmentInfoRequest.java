package com.car.request;

import java.io.Serializable;

public class EnvironmentInfoRequest implements Serializable {
    private static final long serialVersionUID = 1246348312793107622L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
