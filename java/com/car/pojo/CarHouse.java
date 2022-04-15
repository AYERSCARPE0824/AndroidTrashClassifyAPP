package com.car.pojo;

import java.io.Serializable;

public class CarHouse implements Serializable {
    private static final long serialVersionUID = 6347834263573618842L;
    private double temp;
    private double humidity;
    private double co2;
    private double shake;
    private int grade;
    private String status;
    private String recorddate;
    private double longitude;
    private double latitude;

    public int getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = (int)grade;
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

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getShake() {
        return shake;
    }

    public void setShake(double shake) {
        this.shake = shake;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "CarHouse{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", shake=" + shake +
                ", grade=" + grade +
                ", status='" + status + '\'' +
                ", recorddate='" + recorddate + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
