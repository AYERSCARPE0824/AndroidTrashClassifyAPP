package com.car.result;

import com.car.pojo.EnvironmentInfo;

import java.io.Serializable;

public class EnvironmentInfoResult implements Serializable {
    private static final long serialVersionUID = 8496314172383760811L;
    private EnvironmentInfo environmentInfo;
    private boolean res;

    public EnvironmentInfo getEnvironmentInfo() {
        return environmentInfo;
    }

    public void setEnvironmentInfo(EnvironmentInfo environmentInfo) {
        this.environmentInfo = environmentInfo;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "EnvironmentInfoResult{" +
                "environmentInfo=" + environmentInfo +
                ", res=" + res +
                '}';
    }
}
