package com.lemon.java8.steam;

import com.lemon.java8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author carl
 * @Date 2022/1/29 20:51
 * @Version 1.0
 * @Desc   一、Stream 的三个操作步骤：
 *   1.创建 Stream
 *
 *   2.中间操作
 *
 *   3. 终止操作 （终端操作）
 *
 */
public class TestStreamAPI {

    // 创建 stream
    @Test
    public void test1(){
        // 1.   可以通过 Collection 系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.  通过 Arrays 中的静态方法 stram() 获取 数组流
        Employee[] emp = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emp);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stringStream = Stream.of("aa", "bb", "cc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);

    }


}
