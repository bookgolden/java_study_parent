package com.java.cn.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BgiDecimalTest {
	public static void main(String[] args) {

		BigDecimal bd = new BigDecimal(6982.50000000).setScale(3, RoundingMode.HALF_UP).divide(new BigDecimal(58120.76000000), 4);
		BigDecimal bd2 = new BigDecimal(10).setScale(3, RoundingMode.HALF_UP).divide(new BigDecimal(2), 2, BigDecimal.ROUND_DOWN);
		System.out.println(bd);
	}
	
}
