package com.weimob.common.Thread;

import jdk.nashorn.internal.runtime.logging.Logger;

import java.util.Date;

/**
 * @author Curry
 * @date 2019/8/11 19:26
 * @description 线程测试类 继承Thread类（不推荐）
 */
@Logger
public class ThreadTest2 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"当前时间:"+new Date());
        MyThread threadA=new MyThread();
        MyThread threadB=new MyThread();
        threadA.start();
        threadB.start();
    }
}
