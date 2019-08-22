package com.weimob.common.Thread;

import jdk.nashorn.internal.runtime.logging.Logger;

import java.util.Date;

/**
 * @author Curry
 * @date 2019/8/11 19:26
 * @description 线程测试类 开启一个线程的方式
 */
@Logger
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"当前时间:"+new Date());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("线程异常");
                }
                System.out.println(Thread.currentThread().getName()+"当前时间:"+new Date());
            }
        }).start();

        // lambda表达式
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("线程异常");
            }
            System.out.println(Thread.currentThread().getName()+"当前时间:"+new Date());
            // 可以设置线程名称
        },"ThreadA").start();

        // lambda
        new Thread(()->{
            // do something
        }).start();
    }
}
