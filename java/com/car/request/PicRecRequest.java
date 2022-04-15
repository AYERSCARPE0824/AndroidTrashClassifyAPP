package com.car.request;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class PicRecRequest implements Serializable {

    private static final long serialVersionUID = -6841427657609140145L;

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public PicRecRequest(byte[] bytes) {
        this.bytes = bytes;
    }
}