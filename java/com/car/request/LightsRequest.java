package com.car.request;

import com.car.pojo.Lights;

import java.io.Serializable;

public class LightsRequest implements Serializable {
    private static final long serialVersionUID = 306847718397969488L;
    private Lights lights;

    public Lights getLights() {
        return lights;
    }

    public void setLights(Lights lights) {
        this.lights = lights;
    }
}
