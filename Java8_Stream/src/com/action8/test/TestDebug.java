package com.action8.test;

import com.action8.myclass.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName : TestDebug
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-28 15:02
 * @Description : 第8章 重构、测试和调试
 */
public class TestDebug {

    @Test
    public void testMoveRightBy(){
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);

        Assertions.assertEquals(15,p2.getX());
        Assertions.assertEquals(5,p2.getY());

    }

    @Test
    public void test1(){
        List<Integer> number = Arrays.asList(2,3,4,5);
        number.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 ==0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(toList());



    }
}
