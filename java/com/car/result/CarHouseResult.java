package com.car.result;

import com.car.pojo.CarHouse;

import java.io.Serializable;

/**
 * @author jhyang
 * @create 2021-05-21 20:43
 */
public class CarHouseResult implements Serializable {
    private static final long serialVersionUID = 2914799536506153549L;
    private CarHouse carHouse;
    private boolean res;

    public CarHouse getCarHouse() {
        return carHouse;
    }

    public void setCarHouse(CarHouse carHouse) {
        this.carHouse = carHouse;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "CarHouseResult{" +
                "carHouse=" + carHouse +
                ", res=" + res +
                '}';
    }
}
