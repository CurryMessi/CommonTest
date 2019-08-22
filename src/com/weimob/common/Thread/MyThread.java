package com.weimob.common.Thread;

/**
 * @author Curry
 * @date 2019/8/11 20:14
 * @description
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
    }

}
