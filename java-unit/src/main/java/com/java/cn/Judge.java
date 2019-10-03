package com.java.cn;

/**
 * 判定
 */
public class Judge {

    /**
     * 某判定
     */
    public int judge(int a, int b) {
        int nReturn = 0;
        if (a < 10) {//  分支一
            nReturn += 1;
        }
        if (b < 10) {//  分支二
            nReturn += 10;
        }
        return nReturn;
    }
}
