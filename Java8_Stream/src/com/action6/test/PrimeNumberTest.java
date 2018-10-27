package com.action6.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.action6.myclass.PrimeNumber.partitionPrimes;
import static com.action6.myclass.PrimeNumber.partitionPrimesWithCustomCollector2;

/**
 * @ClassName : PrimeNumberTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-26 16:46
 * @Description : 质数测试类
 */
public class PrimeNumberTest {

    /**
     * 质数测试（使用 partitioningBy 工厂方法创建收集器 partitionPrimes()方法）
     */
    @Test
    public void testPrime(){
        Map<Boolean, List<Integer>>  map = partitionPrimes(30);
                for (Map.Entry<Boolean,List<Integer>> number:map.entrySet()){
                    System.out.println(number);
                }
    }

    /**
     * 质数测试（自定义收集器 partitionPrimesWithCustomCollector()方法)
     */
    @Test
    public void testCollectorHarness(){
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10 ; i++){
            long start = System.nanoTime();
            //582
            //partitionPrimes(1_000_000);
            //395
            //partitionPrimesWithCustomCollector(1_000_000);
            //412
            partitionPrimesWithCustomCollector2(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

}
