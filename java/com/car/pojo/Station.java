package com.car.pojo;

import java.io.Serializable;

/**
 * @author jhyang
 * @create 2022-03-30 10:08
 */
public class Station implements Serializable {
    private static final long serialVersionUID = -8430101697813952647L;
    private String sname;
    private int slongitude;
    private int slatitude;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSlongitude() {
        return slongitude;
    }

    public void setSlongitude(int slongitude) {
        this.slongitude = slongitude;
    }

    public int getSlatitude() {
        return slatitude;
    }

    public void setSlatitude(int slatitude) {
        this.slatitude = slatitude;
    }

    public double getDoubleSlongitude() {
        return (double) getSlongitude();
    }

    public double getDoubleSlatitude() {
        return (double) getSlatitude();
    }

    @Override
    public String toString() {
        return "Station{" +
                "sname='" + sname + '\'' +
                ", slongitude=" + slongitude +
                ", slatitude=" + slatitude +
                '}';
    }
}
