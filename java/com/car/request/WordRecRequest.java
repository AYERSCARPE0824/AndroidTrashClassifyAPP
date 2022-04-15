package com.car.request;

import java.io.Serializable;

/**
 * @author jhyang
 * @create 2022-03-29 16:52
 */
public class WordRecRequest implements Serializable {

    private static final long serialVersionUID = -2544666365193689125L;

    private String word;

    public WordRecRequest(String word) {
        this.word = word;
    }

    public String getWord() {

        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
