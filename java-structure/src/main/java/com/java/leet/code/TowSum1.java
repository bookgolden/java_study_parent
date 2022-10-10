package com.java.leet.code;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TowSum1 {

    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] index = getIndex2(nums, target);
        System.out.println(JSON.toJSONString(index));
    }

    int[] getIndex(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<arr.length; i++) {
            int end = target - arr[i];
            if (map.containsKey(end)) {
                return new int[]{i, map.get(end)};
            }
            map.put(arr[i], i);
        }
        return null;
    }

    int[] getIndex2(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return null;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode listNode) {
        this.val = val;
        this.next = listNode;
    }
}