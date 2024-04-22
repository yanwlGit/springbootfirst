package com.wlong.dodata;

import java.io.Serializable;

public class SimpleVO implements Serializable {

    private String text;
    private String val;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
