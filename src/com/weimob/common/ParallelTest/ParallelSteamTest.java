package com.weimob.common.ParallelTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Curry
 * @date 2019/4/20
 */
public class ParallelSteamTest {

    private static List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        //parallelSteamTest();
        test();
    }

    private static void parallelSteamTest() {
        long l = System.currentTimeMillis();
        //创建集合大小为100
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            integers.add(i);
        }

        //多管道遍历
        List<Integer> integerList = new ArrayList<>();
        integers.parallelStream().peek(x-> setInteger(integers, x)).forEach(System.out::println);
        integers.forEach(e -> {
            //添加list的方法
            setInteger(integerList, e);
        });
        System.out.println(integerList.size());
        System.out.println("耗时:"+(System.currentTimeMillis()-l)+"ms");
        // 修改ForkJoinPool默认的并发线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        // 机器cpu数量
        int cpu = Runtime.getRuntime().availableProcessors();
        // parallelStream默认的并发线程数
        int commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism();
        System.out.println("cpu数量:"+cpu+" parallelStream默认的并发线程数:"+commonPoolParallelism);
    }

    private static void setInteger(List<Integer> integerList, Integer e) {
        integerList.add(e);
    }

    private static void test(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::print);

        numbers.parallelStream()
                .forEachOrdered(System.out::print);
    }

    /**
     *在这个例子中，我们想同时调用不同地址的api中并且获得第一个返回的结果。
     */
    public static String query(String q, List<String> engines) {
        Optional<String> result = engines.stream().parallel().map((base) -> {
        String url = base + q;
        return url;
        //return WS.url(url).get();
    }).findAny();
        return result.get();
    }

}
