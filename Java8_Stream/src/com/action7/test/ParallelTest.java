package com.action7.test;

import com.action7.myclass.ParallelStreams;
import org.junit.jupiter.api.Test;

/**
 * @ClassName : ParallelTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-27 10:44
 * @Description : 7.并行数据处理与性能 测试类
 *
 */
public class ParallelTest {


    /**
     *  7.1 并行流
     * 限定数字内所有数字求和（）
     */
    @Test
    public void testNumberSum(){
        System.out.println("1.8:"+ ParallelStreams.squentialSum(10l));
        System.out.println("1.7:"+ ParallelStreams.iterativeSum(10l));
        System.out.println("1.8并行流:"+ ParallelStreams.parallelSum(10l));
        System.out.println("--------------------------");

        System.out.println("Sequential sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::squentialSum,
                        10_000_000) + " msecs");

        System.out.println("Iterative sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::iterativeSum,
                        10_000_000) + " msecs");

        System.out.println("Parallel sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::parallelSum,
                        10_000_000) + " msecs");

        System.out.println("Ranged sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::rangedSum,
                        10_000_000) + " msecs");

        System.out.println("ParallelRanged sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::parallelRangedSum,
                        10_000_000) + " msecs");

        System.out.println("ForkJoin sum done in:" +
                ParallelStreams.measureSumPerf(ParallelStreams::forkJoinSum,
                        10_000_000) + " msecs");


    }
}
