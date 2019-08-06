package com.weimob.common.test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Curry
 * @date 2019/8/6 15:43
 * @description 异步利器 CompletableFuture vs Parallel Stream 选哪个
 */
public class CompletableFutureTest {


    public static void main(String[] args) {
        List<MyTask> tasks = IntStream.range(0, 10)
                .mapToObj(i -> new MyTask(1))
                .collect(Collectors.toList());
        //runSequentially(tasks);
        //useParallelStream(tasks);
        //useCompletableFuture(tasks);
        useCompletableFutureWithExecutor(tasks);
    }

    // 串行执行 正如你所预期的, 它花费了10秒, 因为每个任务在主线程一个接一个的执行
    private static void runSequentially(List<MyTask> tasks) {
        long start = System.nanoTime();
        List<Integer> result = tasks.stream()
                .map(MyTask::calculate)
                .collect(Collectors.toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
    }

    // 使用parallel stream 一个快速的改善方式是使用parallel stream
    // 它花费了2秒，因为此次并发执行使用了8个线程 (7个是ForkJoinPool线程池中的, 加上main 线程)=我电脑的cpu数量
    private static void useParallelStream(List<MyTask> tasks) {
        long start = System.nanoTime();
        List<Integer> result = tasks.parallelStream()
                .map(MyTask::calculate)
                .collect(Collectors.toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
    }

    /**
     *  以上代码，我们首先获取CompletableFutures集合，然后在每个future上调用join方法去等待他们逐一执行完。
     *  注意，join方法类似于get方法，唯一的不通点是前者不会抛出任何的受检查异常，所以在lambda表达式中更方便一些.
     * 再有，你必须使用两个独立的stream(pipelines)管道，而不是将两个map操作放在一起，
     * 因为stream的中间操作都是懒加载的(intermediate stream operations are lazy)，
     * 你最终必须按顺序处理你的任务。这就是为什么首先需要CompletableFuture在list中，然后允许他们开始执行，直到执行完毕.
     */
    // 使用CompletableFutures
    // 它花费了2秒，因为此次并发执行使用了7个线程,比parallel stream少一个main线程
    private static void useCompletableFuture(List<MyTask> tasks) {
        long start = System.nanoTime();
        List<CompletableFuture<Integer>> futures = tasks.stream()
                        .map(t -> CompletableFuture.supplyAsync(() -> t.calculate()))
                        .collect(Collectors.toList());

        List<Integer> result = futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
    }

    /**
     * CompletableFutures比parallel streams优点之一是你可以指定不用的Executor去处理他们的任务。这意味着基于你的项目，你能选择更合适数量的线程。
     * 我的例子不是cpu密集型的任务，我能选择增加大于Runtime.getRuntime().getAvailableProcessors()数量的线程，如下所示
     */
    // 使用带有自定义Executor的CompletableFuture
    // 在这次改进之后，它花费了1秒去处理这10个任务.一共是10个线程;
    // 正如你看到的，CompletableFutures可以更多的控制线程池的数量;
    // 如果你的任务是io密集型的，你应该使用CompletableFutures;
    // 如果你的任务是cpu密集型的，使用比处理器更多的线程是没有意义的，所以选择parallel stream，因为它更容易使用.
    private static void useCompletableFutureWithExecutor(List<MyTask> tasks) {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
        List<CompletableFuture<Integer>> futures =
                tasks.stream()
                        .map(t -> CompletableFuture.supplyAsync(() -> t.calculate(), executor))
                        .collect(Collectors.toList());

        List<Integer> result = futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
        executor.shutdown();
    }

    /**
     * CPU密集型（CPU-bound）
     * CPU密集型也叫计算密集型，指的是系统的硬盘、内存性能相对CPU要好很多，此时，系统运作大部分的状况是CPU Loading 100%，CPU要读/写I/O(硬盘/内存)，I/O在很短的时间就可以完成，而CPU还有许多运算要处理，CPU Loading很高。
     * 在多重程序系统中，大部份时间用来做计算、逻辑判断等CPU动作的程序称之CPU bound。例如一个计算圆周率至小数点一千位以下的程序，在执行的过程当中绝大部份时间用在三角函数和开根号的计算，便是属于CPU bound的程序。
     * CPU bound的程序一般而言CPU占用率相当高。这可能是因为任务本身不太需要访问I/O设备，也可能是因为程序是多线程实现因此屏蔽掉了等待I/O的时间。
     */

    /**
     * IO密集型---一般WEB项目都是IO密集型与数据库的交互较多
     * IO密集型指的是系统的CPU性能相对硬盘、内存要好很多，此时，系统运作，大部分的状况是CPU在等I/O (硬盘/内存) 的读/写操作，此时CPU Loading并不高。
     * I/O bound的程序一般在达到性能极限时，CPU占用率仍然较低。这可能是因为任务本身需要大量I/O操作，而pipeline做得不是很好，没有充分利用处理器能力。
     */

    /**
     * 我们可以把任务分为计算密集型和IO密集型。
     *
     * 计算密集型任务的特点是要进行大量的计算，消耗CPU资源，比如计算圆周率、对视频进行高清解码等等，全靠CPU的运算能力。这种计算密集型任务虽然也可以用多任务完成，
     * 但是任务越多，花在任务切换的时间就越多，CPU执行任务的效率就越低，所以，要最高效地利用CPU，计算密集型任务同时进行的数量应当等于CPU的核心数。
     *
     * 计算密集型任务由于主要消耗CPU资源，因此，代码运行效率至关重要。Python这样的脚本语言运行效率很低，完全不适合计算密集型任务。对于计算密集型任务，最好用C语言编写。
     *
     * 第二种任务的类型是IO密集型，涉及到网络、磁盘IO的任务都是IO密集型任务，这类任务的特点是CPU消耗很少，任务的大部分时间都在等待IO操作完成（因为IO的速度远远低于CPU和内存的速度）。
     * 对于IO密集型任务，任务越多，CPU效率越高，但也有一个限度。常见的大部分任务都是IO密集型任务，比如Web应用。
     *
     * IO密集型任务执行期间，99%的时间都花在IO上，花在CPU上的时间很少，因此，用运行速度极快的C语言替换用Python这样运行速度极低的脚本语言，完全无法提升运行效率。
     * 对于IO密集型任务，最合适的语言就是开发效率最高（代码量最少）的语言，脚本语言是首选，C语言最差。
     *
     * 总之，计算密集型程序适合C语言多线程，I/O密集型适合脚本语言开发的多线程。
     */
}
