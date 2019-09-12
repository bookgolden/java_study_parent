package com.java.cn.bigdecimal;

public class StringCompare implements Comparable<StringCompare> {
    private String mStr;

    public StringCompare(String string) {
        this.mStr = string;
    }

    @Override
    public boolean compareTo(StringCompare str) {
        if (mStr.length() > str.mStr.length()) {
            return true;
        }
        return false;
    }


}