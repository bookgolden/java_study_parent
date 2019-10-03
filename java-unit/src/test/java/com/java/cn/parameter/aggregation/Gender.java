package com.java.cn.parameter.aggregation;

/** 性别 */
public class Gender {
    public static final Gender Male = new Gender("male"); // 男性
    public static final Gender FeMale = new Gender("female"); // 女性

    private String gender;

    public Gender(String s) {
        this.gender = s;
    }

    public String getGender() {
        return this.gender;
    }

    public static Gender parse(String str) {
        System.out.println("工厂方法构建Gender");
        if (str.equals("F")) {
            return FeMale;
        } else if (str.equals("M")) {
            return Male;
        }
        return null;
    }
}
