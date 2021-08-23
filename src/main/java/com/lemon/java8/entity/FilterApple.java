package com.lemon.java8.entity;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author: Lemon
 * @Date : 2021/8/23 21:05
 * 
 */
public class FilterApple {

    @FunctionalInterface
    public interface  AppleFilter{
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for (Apple apple: apples) {
            if (appleFilter.filter(apple)){
                list.add(apple);
            }
        }

        return list;
    }

    public static class GreenAnd160weightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return  (apple.getColor().equals("green") && apple.getWeight()>=160);
        }
    }

    public static class YellowLess150weightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return  (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    public static List<Apple> findGreenApple(List<Apple> apples){
        List<Apple> list = new ArrayList<>();
        for (Apple apple: apples) {
            if ("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color){
        List<Apple> list = new ArrayList<>();
        for (Apple apple: apples){
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    @SneakyThrows
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green",150), new Apple("yellow",120), new Apple("green",170));
        /*List<Apple> greenApples = findGreenApple(list);
        assert greenApples.size() == 1;
        System.out.println(greenApples);*/

        /*List<Apple> green = findApple(list, "green");
        System.out.println(green);

        List<Apple> red = findApple(list, "red");
        System.out.println(red);*/

        /*List<Apple> apples = findApple(list, new GreenAnd160weightFilter());
        System.out.println(apples);*/

        // 匿名内部类
        /*List<Apple> yellows = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });

        System.out.println(yellows);*/

      /*  List<Apple> apples = findApple(list, apple -> {
            return apple.getColor().equals("green");
        });
        System.out.println(apples);*/

        /*Thread thread = */new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        
        Thread.currentThread().join();
    }
}
