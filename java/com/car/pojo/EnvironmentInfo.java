package com.car.pojo;

import java.io.Serializable;

public class EnvironmentInfo implements Serializable {
    private static final long serialVersionUID = -7301576350741159275L;
    private double temp;
    private double humidity;
    private int ultra_distance;

    public int getUltra_distance() {
        return ultra_distance;
    }

    public void setUltra_distance(int ultra_distance) {
        this.ultra_distance = ultra_distance;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "EnvironmentInfo{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", ultra_distance=" + ultra_distance +
                '}';
    }
}
