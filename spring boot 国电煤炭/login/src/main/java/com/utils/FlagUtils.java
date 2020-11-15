package com.utils;

import org.springframework.stereotype.Component;

@Component
public class FlagUtils {

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
