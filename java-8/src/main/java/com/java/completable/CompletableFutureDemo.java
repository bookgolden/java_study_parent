package com.java.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 1、runXxx都是没有返回结果的，supplyXxx 都是可以获取返回结果的
     * 2、可以传入自定义的线程池，否则就用默认的线程池；
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");

//        CompletableFuture.runAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//        }, executorService);

//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10;
//        }, executorService);
//        Integer integer = completableFuture.get();
//        System.out.println(integer);

//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10 / 0;
//        }, executorService).whenComplete((result, exception) -> {
//            System.out.println("result = " + result + " exception = " + exception);
//        }).exceptionally((throwable) -> 9);
//        Integer integer = completableFuture.get();
//        System.out.println(integer);

//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10 / 0;
//        }, executorService).whenComplete((result, exception) -> {
//            System.out.println("result = " + result + " exception = " + exception);
//        }).handle((result, exception) -> {
//            if (result != null) {
//                System.out.println("result is not null");
//                return result;
//            }
//            if (exception != null) {
//                System.out.println("exception is not null" + exception);
//                return 0;
//            }
//            return 0;
//        });
//        Integer integer = completableFuture.get();
//        System.out.println(integer);

//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10 / 2;
//        }, executorService).thenRunAsync(() -> {
//            System.out.println("任务2启动了");
//        }, executorService);

//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10 / 2;
//        }, executorService).thenAcceptAsync((result) -> {
//            System.out.println("任务2启动了, result: " + result);
//        }, executorService);

//        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println("currentThread: " + Thread.currentThread().getId());
//            return 10 / 2;
//        }, executorService).thenApplyAsync((result) -> {
//            System.out.println("任务2启动了, result: " + result);
//            return result * 2;
//        }, executorService);
//        System.out.println("result: " + integerCompletableFuture.get());

//        CompletableFuture<Object> future1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务1开始: " + Thread.currentThread().getId());
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            int i = 10 / 4;
//            System.out.println("任务1结束");
//            return i;
//        }, executorService);
//
//        CompletableFuture<Object> future2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务2线程: " + Thread.currentThread().getId());
//            return "hello";
//        }, executorService);

//        CompletableFuture<Void> completableFuture = future1.runAfterBothAsync(future2, () -> {
//            System.out.println("任务3开始");
//        }, executorService);

//        CompletableFuture<Void> completableFuture = future1.thenAcceptBothAsync(future2, (f1, f2) -> {
//            System.out.println("任务3开始...之前的结果： " + f1 + " --> " + f2);
//        }, executorService);

//        CompletableFuture<String> completableFuture = future1.applyToEitherAsync(future2, result -> {
//            System.out.println("任务3开始...之前的结果：" + result);
//            return result.toString() + "ha ha";
//        });
//        System.out.println("final4 : " + completableFuture.get());

        CompletableFuture<String> futureImage = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1);
                System.out.println("查询商品 - 1、介绍");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("查询商品 - 1、图片信息");
            return "hello.jpg";
        }, executorService);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品 - 2、属性");
            return "black+256G";
        }, executorService);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("查询商品 - 3、品牌");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "huaWei";
        }, executorService);

        // CompletableFuture<Void> future = CompletableFuture.allOf(futureImage, futureAttr, futureDesc);
        CompletableFuture<Object> future = CompletableFuture.anyOf(futureImage, futureAttr, futureDesc);
        System.out.println("---------- result : " + future.get());

        System.out.println("main...end...");
    }

}
