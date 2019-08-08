package com.java.cn.lambda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

class DescSort implements Comparator<String> {
    public int compare(String s1, String s2) {
    	return s1.compareTo(s2);
    }
}

public class Test {
	
	public static void main(String[] args) {
//		List<Integer> list = getHitPrizeList(40, 9);
//		System.out.println(list.toString());
		
//		Set<String> ts = new TreeSet<>(new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareTo(o2);
//			}
//		});
//		
//		ts.add("111");
//		ts.add("2");
//		ts.add("5");
//		ts.add("6");
//		ts.add("3");
//		
//		for(Iterator<String> iter = ts.iterator(); iter.hasNext();) {
//			System.out.println(iter.next());
//		}

//		SortedSet<Integer> ts = new TreeSet<Integer>();
//		TreeSet<Integer> ts = new TreeSet<Integer>();
//		ts.add(10);
//		ts.add(new Random().nextInt(100));
//		ts.add(6);
//		ts.add(3);
//		System.out.println(ts);
		
//		TreeMap<Integer, String> treeMap = new TreeMap<>();
//		treeMap.put(2, "two");
//		treeMap.put(1, "one");
//		treeMap.put(3, "three");
//		treeMap.put(10, "ten");
//		treeMap.put(5, "five");
		
		TreeMap<String, String> treeMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeMap.put("2", "two");
		treeMap.put("1", "one");
		treeMap.put("3", "three");
		treeMap.put("10", "ten");
		treeMap.put("5", "five");
		System.out.println(treeMap.toString());
		
		LocalDate localDate = LocalDate.of(2016, 10, 26);
		LocalTime localTime = LocalTime.of(02, 22, 56);
		LocalDateTime localDateTime = LocalDateTime.of(2016, 10, 26, 12, 10, 55);
		
		LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
		System.out.println(nextSunday.toString());
		
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
