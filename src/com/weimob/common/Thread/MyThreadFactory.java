package com.weimob.common.Thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Curry
 * @date 2019/8/12 23:04
 * @description 自定义线程工厂
 */
public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger count=new AtomicInteger(0);
    @Override
    public Thread newThread(Runnable r) {
       Thread thread=new Thread();
       thread.setName(MyThreadFactory.class.getSimpleName() + count.addAndGet(1));
       return thread;
    }
}
