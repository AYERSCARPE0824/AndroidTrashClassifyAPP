package com.car.result;

import com.car.pojo.Station;

import java.io.Serializable;
import java.util.List;

/**
 * @author jhyang
 * @create 2022-03-30 10:34
 */
public class StationsResult implements Serializable {
    private static final long serialVersionUID = -3774903812326300201L;

    private int num;
    private List<Station> stations;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
