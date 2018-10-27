package com.action7.myclass;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @ClassName : ParallelStreams
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-27 10:48
 * @Description : 测试方法  7.1 并行流
 */
public class ParallelStreams {


    /**
     *
     * 对两个数字求和的 BinaryOperator  归约此流
     * @param n
     * @return
     */
    public static long squentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 传统Java术语
     */
    public static long iterativeSum(long n){
        long result = 0;
        for(long i =1L ;i <= n ; i++){
            result += i;
        }
        return result;
    }


    /**
     * 7.1.1 将顺序流转换为并行流 parallel()
     * @param n
     * @return
     */
    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i +1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }

    /**
     * 7-1 测量对前n个自然数求和的函数的性能
     * @param addr
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long,Long> addr, long n){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = addr.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    /**
     * LongStream.rangeClosed 直接产生原始类型的long数字，没有装箱拆箱的开销
     * 会生成数字范围，很容易拆分为独立的小块，例，范围1~20 可分为1~5,6~10,11~15,16~20.
     */
    public static long rangedSum(long n){
        return LongStream.rangeClosed(1,n).reduce(0L,Long::sum);
    }

    /**
     * 优化应用并行流
     * @param n
     * @return
     */
    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1,n).parallel().reduce(0L,Long::sum);
    }

    /**
     * 分支/合并框架 7.2.1使用RecursiveTask
     * @param n
     * @return
     */
    public static long forkJoinSum(long n){
        long [] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
