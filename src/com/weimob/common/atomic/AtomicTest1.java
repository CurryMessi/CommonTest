package com.weimob.common.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Curry
 * @date 2019/8/13 22:02
 * @description Atomic 原子操作类
 */
public class AtomicTest1 {

    AtomicInteger INTEGER=new AtomicInteger(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // JDK8AtomicInteger count = new AtomicInteger(); count.addAndGet(1);
        // 如果是 JDK8，推 荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）。
        LongAdder longAdder=new LongAdder();
        longAdder.reset();
        AtomicInteger x= new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            executorService.execute(()->{
                longAdder.increment();
                int num=longAdder.intValue();
                System.out.println("线程name="+Thread.currentThread().getName()+":i="+x.addAndGet(1));
            });
        }
    }
}
