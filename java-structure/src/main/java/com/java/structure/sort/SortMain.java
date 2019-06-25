package com.java.structure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SortMain {
    public static void main(String[] args) {
//        int arr[] = { 53, 3, 542, 748, 14, 214};

        // 创建要给80000个的随机的数组
        // 80000000 * 11 * 4 / 1024 / 1024 / 1024 =3.3G
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

        System.out.println("排序前");
        System.out.println("排序前时间是=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //1、冒泡排序
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);

        //2、选择排序
        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(arr);

        //3、插入排序
        InsertSort insertSort = new InsertSort();
        insertSort.insertSort(arr);

        //4、希尔排序
        ShellSort shellSort = new ShellSort();
        shellSort.shellSort(arr);   //交换式
        shellSort.shellSort2(arr);  //移位方式

        //5、快速排序
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr, 0, arr.length-1);

        //6、归并排序
        MergetSort mergetSort = new MergetSort();
        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        mergetSort.mergeSort(arr, 0, arr.length - 1, temp);

        //7、基数排序
        RadixSort radixSort = new RadixSort();
        radixSort.radixSort(arr);

        System.out.println("排序后时间是=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        System.out.println("基数排序后 " + Arrays.toString(arr));

    }
}
