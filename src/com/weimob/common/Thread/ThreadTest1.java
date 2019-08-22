package com.weimob.common.Thread;

import jdk.nashorn.internal.runtime.logging.Logger;

import java.util.Date;

/**
 * @author Curry
 * @date 2019/8/11 19:26
 * @description 线程测试类 实现Runnable接口（推荐）
 */
@Logger
public class ThreadTest1 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"当前时间:"+new Date());
        MyRunnable myRunnable1=new MyRunnable();
        MyRunnable myRunnable2=new MyRunnable();
        // 自定义线程名称
        Thread threadA=new Thread(myRunnable1);
        threadA.start();
        new Thread(myRunnable1).start();
        new Thread(myRunnable2).start();
    }
}
