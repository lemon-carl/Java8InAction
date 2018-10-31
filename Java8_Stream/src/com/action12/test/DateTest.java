package com.action12.test;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * @ClassName : DateTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-31 15:37
 * @Description : 第12章、日期时间测试
 * 12.1 LocalDate 、LocalTime 、Instant、Duration、 Period
 * 12.2操纵、解析和格式化日期
 * 12.3处理不同的时区和历法
 */
public class DateTest {

    /**
     * 12.1.1 使用 LocalDate 和 LocalTime
     */
    @Test
    public void testDateAndTime(){
        LocalDate date = LocalDate.of(2018,10,31);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.println(year + "-"+ month + "-" + day
                +" " +"周" + dow + "该月共"+len+"天，" +" 是闰年：" + leap);

        //获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println(today);

        //使用 枚举 ChronoField implements TemporalField
        int year2 = date.get(ChronoField.YEAR);
        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day2 = date.get(ChronoField.DAY_OF_MONTH);

        System.out.println(year2+"-"+month2+"-"+day2);

        System.out.println("-----------LocalTime---------------------");
        //LocalTime
        LocalTime time = LocalTime.of(15,59,20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("时分秒： " +hour+":"+minute+":"+second);

        System.out.println("----------parse 解析--------------");
        //字符串创建 静态方法 parse 解析
        LocalDate date2 = LocalDate.parse("2018-08-08");
        LocalTime time2 = LocalTime.parse("16:01:10");

        System.out.println(date2+ " " + time2);


    }

    /**
     * 12.1.2 合并日期和时间
     */
    @Test
    public void testLocalDateTime(){
        //2018-10-31T16:15:20
        LocalDate date = LocalDate.of(2018,10,31);
        LocalTime time = LocalTime.of(16,15,20);
        LocalDateTime dateTime1 = LocalDateTime.of(2018,Month.OCTOBER,31,16,15,20);
        LocalDateTime dateTime2 = LocalDateTime.of(date,time);
        LocalDateTime dateTime3 = date.atTime(16,01,20);
        LocalDateTime dateTime4 = date.atTime(time);
        LocalDateTime dateTime5 = time.atDate(date);

        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(dateTime3);
        System.out.println(dateTime4);
        System.out.println(dateTime5);

        System.out.println("---------------------");
        LocalDate date1 = dateTime1.toLocalDate();
        LocalTime time1 = dateTime1.toLocalTime();
        System.out.println(date1 + " " + time1);
    }

    /**
     * 12.1.3 机器的日期和时间格式
     *  java.time.Instant
     */
    @Test
    public void testInstant(){
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3,0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);

        //java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
        /*int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
        System.out.println(day);*/

    }

    /**
     * 12.1.4 定义 Duration或 Period
     */
    @Test
    public void testDurationOrPeriod(){
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes + " " + threeMinutes2);


        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2,6,1);
        System.out.println(tenDays);
        System.out.println(threeWeeks);
        System.out.println(twoYearsSixMonthsOneDay);
    }

    /**
     * 12.2.5  打印输出以及解析日期-时间对象
     */
    @Test
    public void testDateTimeFormatter(){
        LocalDate date = LocalDate.of(2018,10,18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(s1);
        System.out.println(s2);

        LocalDate date1 = LocalDate.parse("20181011",DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2018-10-12",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1);
        System.out.println(date2);

        //按照某个模式创建 DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.of(2014,3,18);
        String formattedDate = date3.format(formatter);
        LocalDate date4 = LocalDate.parse(formattedDate,formatter);
        System.out.println(formattedDate); //18/03/2014
        System.out.println(date4); // 2014-03-18

        //创建一个本地化的 DateFormatter
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date5 = LocalDate.of(2014,3,18);
        String formattedDate2 = date5.format(italianFormatter);
        LocalDate date6 = LocalDate.parse(formattedDate2,italianFormatter);
        System.out.println(formattedDate2);// 18. marzo 2014
        System.out.println(date6);// 2014-03-18


        //使用 DateTimeFormatterBuilder 格式器
        DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        String formattedDate3 = date5.format(italianFormatter);
        LocalDate date7 = LocalDate.parse(formattedDate2,italianFormatter);
        System.out.println(formattedDate3);// 18. marzo 2014
        System.out.println(date7);// 2014-03-18

    }

}
