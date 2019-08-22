package com.weimob.common.Thread;

import java.util.Date;

/**
 * @author Curry
 * @date 2019/8/11 20:17
 * @description
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // do something
        System.out.println(Thread.currentThread().getName()+"当前时间"+new Date());
    }
}
