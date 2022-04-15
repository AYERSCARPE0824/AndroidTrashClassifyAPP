package com.car.result;

import java.io.Serializable;

/**
 * @author jhyang
 * @create 2022-03-29 16:11
 */
public class RecResult implements Serializable {

    private static final long serialVersionUID = -5770455060941170854L;

    public static final int WORD_REC_RESULT = 1;
    public static final int PIC_REC_RESULT = 2;
    private int resultType;
    private String className;
    private boolean res;

    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
}
