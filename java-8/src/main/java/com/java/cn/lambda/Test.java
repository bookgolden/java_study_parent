package com.java.cn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> list = getHitPrizeList(40, 9);
		System.out.println(list.toString());
	}

	 /**
     * 获取中奖号码
     * @param prizeNoRange
     * @param needTotalPrizer 需要获取多少个中奖号码
     * @return
     */
    public static List<Integer> getHitPrizeList(int prizeNoRange, int needTotalPrizer) {
    	 List<Integer> prizeList = new ArrayList<>();
    	 
    	 //抽奖个数不能大于指定的抽奖范围，否则就会java.lang.StackOverflowError
    	if(prizeNoRange < needTotalPrizer){
    		needTotalPrizer = prizeNoRange;
    	}
       
        for (int i = 0; i < needTotalPrizer; i++) {
            shakeNo(prizeList, prizeNoRange);
        }
        return prizeList;
    }

    /**
     * 摇奖，不得出现重复号码，否则再来，直到不重复为至
     * @param prizeList
     * @param prizeNoRange
     * @return
     */
    public static void shakeNo(List<Integer> prizeList, int prizeNoRange) {
    	int hitNo = new Random().nextInt(prizeNoRange);
        if (!prizeList.contains(hitNo)) {
        	prizeList.add(hitNo);
        	return;
        }
        shakeNo(prizeList, prizeNoRange);
    }
}
