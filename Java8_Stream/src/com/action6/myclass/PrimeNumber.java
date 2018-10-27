package com.action6.myclass;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @ClassName : PrimeNumber
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-26 16:40
 * @Description :
 */
public class PrimeNumber {

    /*public boolean isPrime(int cnadidate){
        return IntStream.range(2,cnadidate).noneMatch(i -> cnadidate % i ==0);
    }*/

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A item : list){
            if(!p.test(item)){
                return  list.subList(0,i);
            }
            i++;
        }
        return  list;
    }

    /**
     * 单个参数
     * @param candidate
     * @return
     */
    public static  boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateRoot)
                        .noneMatch(i -> candidate % i ==0);

    }

    /**
     * 两个参数
     * @param primes
     * @param candidate
     * @return
     */
    public static  boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p ==0);
    }

    /**
     * 使用 partitioningBy 工厂方法创建收集器
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }

    /**
     * 自定义收集器
     * @param n
     * @return
     */
    public static Map<Boolean,List<Integer>> partitionPrimesWithCustomCollector(int n){
        return  IntStream.rangeClosed(2, n).boxed()
                         .collect(new PrimeNumbersCollector());
    }

    /**
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(() -> new HashMap<Boolean, List<Integer>>() {
                            {
                                put(true, new ArrayList<Integer>());
                                put(false, new ArrayList<Integer>());
                            }
                        }, (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate))
                                    .add(candidate);
                        }, (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        }
                );
    }



}
