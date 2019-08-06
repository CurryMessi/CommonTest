package com.weimob.common.test;

/**
 * @author Curry
 * @date 2019/8/6 15:42
 */
public class MyTask {
    private final int duration;

    public MyTask(int duration) {
        this.duration = duration;
    }
    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return duration;
    }
}
