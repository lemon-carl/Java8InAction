package com.lemon.java8.date;

import org.junit.Test;

import java.time.*;

/**
 * @Desc
 * @Author carl
 * @Date 2022/2/2 19:02
 * @Version 1.0
 */
public class TestLocalDateTime {

    /**
     * 3.
     * Duration: 计算两个时间之间的间隔
     * Period： 计算两个日期之前的间隔
     */
    @Test
    public void test4() {
        LocalDate localDate = LocalDate.of(2021, 02, 02);
        LocalDate localDate1 = LocalDate.now();

        Period between = Period.between(localDate, localDate1);
        System.out.println(between);
    }


    @Test
    public void test3() {
        Instant instant = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println(duration.toMillis());

        System.out.println("---------------------------------------------");

        LocalTime localTime = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime1 = LocalTime.now();

        System.out.println(Duration.between(localTime, localTime1).toMillis());


    }


    /**
     * 2. Instant : 时间戳 （以 unix 元年 1970年1月1日 00:00:00 到某个时间之间的毫秒值）
     */
    @Test
    public void test2() {
        // 默认 UTC 时区
        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(instant.toEpochMilli());

        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);
    }

    /**
     * 1. LocalDate LocalTime  LocalDateTime
     */
    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 02, 02, 19, 28, 40);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime.minusMonths(2);
        System.out.println(localDateTime3);
    }
}
