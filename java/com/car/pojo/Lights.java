package com.car.pojo;

import java.io.Serializable;

public class Lights implements Serializable {
    private static final long serialVersionUID = -9049784523090086743L;
    private boolean lightOne = false;
    private boolean lightTwo = false;
    private boolean lightThree = false;

    public Lights() {
    }

    public Lights(boolean lightOne, boolean lightTwo, boolean lightThree) {
        this.lightOne = lightOne;
        this.lightTwo = lightTwo;
        this.lightThree = lightThree;
    }

    public boolean getLightOne() {
        return lightOne;
    }

    public void setLightOne(boolean lightOne) {
        this.lightOne = lightOne;
    }

    public boolean getLightTwo() {
        return lightTwo;
    }

    public void setLightTwo(boolean lightTwo) {
        this.lightTwo = lightTwo;
    }

    public boolean getLightThree() {
        return lightThree;
    }

    public void setLightThree(boolean lightThree) {
        this.lightThree = lightThree;
    }
}
