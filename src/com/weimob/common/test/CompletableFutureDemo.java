package com.weimob.common.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Curry
 * @date 2019/8/12 14:19
 * @description CompletableFuture
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //future();
        //completableFuture();
        completableFuture1();
    }

    private static void completableFuture1() {
        String[] list={"a","b","c"};
        List<CompletableFuture> resList=new ArrayList<>();
        for (String s : list) {
            resList.add(CompletableFuture.supplyAsync(() -> s).thenApply(String::toUpperCase));
        }
        //allOf接收一个数组，当里面的CompletableFuture都完成的时候，就会执行下一个语句
        //anyOf接收一个数组，当里面的CompletableFuture有一个完成时，就会执行下一个语句
        CompletableFuture.allOf(resList.toArray(new CompletableFuture[resList.size()])).whenComplete((x,e)->{
            if (null==e) {
            }else {
                throw new RuntimeException();
            }
        });
    }

    /**
     * 当前阶段正常完成以后执行，而且当前阶段的执行的结果会作为下一阶段的输入参数。
     * thenApplyAsync默认是异步执行的。这里所谓的异步指的是不在当前线程内执行。
     */
    private static void completableFuture() throws ExecutionException, InterruptedException  {
        CompletableFuture.supplyAsync(()->1).
                thenApplyAsync(i->i+1).
                thenApplyAsync(i->i*i).
                whenComplete((x,e)-> System.out.println(x));

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello").
                thenApplyAsync(s -> s + "world").
                thenApplyAsync(String::toUpperCase);
        String s = future.get();
        System.out.println(s);

        // thenCombine整合两个计算结果
        String str = CompletableFuture.supplyAsync(() -> "hello").
                thenApplyAsync(x -> x + "world").
                thenApplyAsync(String::toUpperCase).
                thenCombine(CompletableFuture.completedFuture("Java"), (s1, s2) -> s1 + s2).join();

        // thenCombine整合两个计算结果
        CompletableFuture.supplyAsync(() -> "hello").
                thenApplyAsync(x -> x+ "world").
                thenApplyAsync(String::toUpperCase).
                thenCombine(CompletableFuture.completedFuture("Java"), (s1,s2)->s1+s2).thenAccept(System.out::println);

    }

    private static void future() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        System.out.println(future.get());
        System.out.println("finish!!!");
    }
}
