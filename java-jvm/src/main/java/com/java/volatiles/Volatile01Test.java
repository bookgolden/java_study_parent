package com.java.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * volatile可见性
 */
public class Volatile01Test {
    //volatile的可见性
    public static void main(String[] args) {
        MyData myData = new MyData();
        //第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                //线程暂停3s
                TimeUnit.SECONDS.sleep(1);
                myData.addTo60();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t update  value:" + myData.number + ", ==========ok");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread1").start();
        //第二个线程是main线程
        while (myData.number == 0) {
            //如果myData的num一直为零，main线程一直在这里循环
            System.out.println(Thread.currentThread().getName() + "\t mission is over, num value is " + myData.number);
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
