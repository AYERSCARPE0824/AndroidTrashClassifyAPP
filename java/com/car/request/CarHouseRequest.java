package com.car.request;

import java.io.Serializable;

public class CarHouseRequest implements Serializable {
    private static final long serialVersionUID = 7940976136870332104L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
