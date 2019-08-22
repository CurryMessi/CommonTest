package com.weimob.common.Thread;

import java.util.stream.IntStream;

/**
 * @author Curry
 * @date 2019/8/11 21:20
 * @description 守护线程测试
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                IntStream.range(0, 5).forEach(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\ti=" + i);
                });
            }
        };
        // 设置守护线程:如果主线程死亡，守护线程如果没有执行完毕也要跟着一块死
        // 不设置默认为用户线程：如果主线程main停止掉，不会影响用户线程，用户线程可以继续运行。
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + "\ti=" + i);
        }
        System.out.println("主线程死亡，子线程也要陪着一块死！");
    }

}
