package com.weimob.common.threadlocal;

/**
 * @author Curry
 * @date 2019/8/13 23:59
 * @description
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal1.set("A");
            threadLocal2.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+threadLocal1.get()+threadLocal2.get());
        },"threadA").start();


        new Thread(() -> {
            threadLocal1.set("B");
            threadLocal2.set(2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+threadLocal1.get()+threadLocal2.get());
        },"threadB").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+threadLocal1.get()+threadLocal2.get());
        },"threadC").start();
    }
}
