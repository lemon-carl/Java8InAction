package com.action6.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName : TestJava8 Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2018-10-22 12:58 @Description :
 */
public class TestJava8 {

    /**
     * //内部迭代输出集合中字符串
     */
    @Test
    public void testStr(){

        List<String> title = Arrays.asList("java8","In" ,"Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        // s.forEach(System.out::println);

    }

    // -=-----------------------------映射---------------


    /**
     *  map函数接收一个字符串，返回其长度
     */
    @Test
    public void test2() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordlengths = words.stream().map(String::length).collect(toList());
        for (Integer s : wordlengths) {
            System.out.println(s);
        }
    }

    /**
     * 流的扁平化，map和flatMap
     */
    @Test
    public void test3() {
        //Arrays.stream字符流
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        uniqueCharacters.forEach(System.out::println);
    }

    /**
     * //给定一个数字列表，返回一个由每个数字的平方构成的列表,
     * 例如，给定【1,2,3,4,5】,应返回【1,4,9,16,25】.
     */
    @Test
    public void test4() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(toList());
        squares.forEach(System.out::println);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对。
     * 例如，列表[1,2,3]和[3,4],应该返回[(1,3),(1,4),(2,3),(2,4)(3,3)(3,4)].
     */
    @Test
    public void test5() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                ).collect(toList());
        pairs.forEach(System.out::println);
    }
}






