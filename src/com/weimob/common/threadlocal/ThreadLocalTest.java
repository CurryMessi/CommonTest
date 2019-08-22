package com.weimob.common.threadlocal;

/**
 * @author Curry
 * @date 2019/8/13 23:17
 * @description ThreadLocal测试
 */
public class ThreadLocalTest {
    //private static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
    private static ThreadLocal<Integer> local = new ThreadLocal<>();
    public static void main(String[] args) {
        for (int i = 0; i <5; i++) {
            new Thread(()->{
                local.set(10);
                Integer integer = local.get();
                for (int x = 0; x <5; x++){
                    System.out.println(Thread.currentThread().getName()+"integer="+integer);
                    integer=integer-1;
                }
//                local.set(10);
//                System.out.println(Thread.currentThread().getName()+ local.get());
            }).start();
        }
    }
}
