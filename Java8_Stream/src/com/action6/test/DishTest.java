package com.action6.test;

import com.action6.myclass.ToListCollector;
import com.action6.stream.Dish;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @ClassName : DishTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-24 14:47
 * @Description : 菜单测试类
 */
public class DishTest {

     public enum CaloricLevel {DIET,NORMAL,FAT}

     List<Dish> menu = Arrays.asList(
             new Dish("pork",false,800,Dish.Type.MEAT),
             new Dish("beef",false,700,Dish.Type.MEAT),
             new Dish("chicken",false,400,Dish.Type.MEAT),
             new Dish("french fruit",true,530,Dish.Type.OTHER),
             new Dish("rice",true,350,Dish.Type.OTHER),
             new Dish("season fruit",true,120,Dish.Type.OTHER),
             new Dish("pizza",true,550,Dish.Type.OTHER),
             new Dish("prawns",false,300,Dish.Type.FISH),
             new Dish("salmon",false,450,Dish.Type.FISH)
     );

     /**
      *第六章用流收集数据、
      * 6.2归约和汇总
      */
     @Test
     public void testCollectors(){
          //归约 Collectors.counting()
          long howMayDishes = menu.stream().collect(counting());
          System.out.println("总共菜品："+ howMayDishes+"种。");

          long howMayDishes2 = menu.stream().count();
          System.out.println("总共菜品："+ howMayDishes2+"种。");

         //查找流中最大值和最小值 maxBy() minBy()
          Comparator<Dish> dishCaloriesComparator =
                  comparingInt(Dish::getCalories);
          Optional<Dish> mostCaloriesDish =
                  menu.stream().collect(maxBy(dishCaloriesComparator));

          System.out.println("菜单中卡路里最高菜："+mostCaloriesDish);

          //汇总求和 Collectors.summingInt
          int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
          System.out.println("菜单总热量："+totalCalories);

          //汇总/平均数 Collectors.averagingInt
          double avgCaloies =
                  menu.stream().collect(averagingInt(Dish::getCalories));
          System.out.println("卡路里平均值：" + avgCaloies);

          //summarizing工厂方法count,sum min avg,max
          IntSummaryStatistics menuStatistics =
                  menu.stream().collect(summarizingInt(Dish::getCalories));
          System.out.println(menuStatistics);

          //连接字符串
          String shortMenu = menu.stream().map(Dish::getName).collect(joining());
          System.out.println(shortMenu);
          String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));
          System.out.println(shortMenu2);

          //Collectors.reducing
          int totalCalories2 = menu.stream()
                  .collect(reducing(
                          0,Dish::getCalories, (i,j) -> i + j));
          System.out.println(totalCalories2);


          //单参数形式
          Optional<Dish> mostCaloriesDish2 =
                  menu.stream().collect(reducing(
                          (d1,d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
          System.out.println(mostCaloriesDish2);


          int totalCalories3 = menu.stream()
                  .collect(reducing(
                          0,Dish::getCalories,Integer::sum));
          System.out.println(totalCalories3);

          //.reduce(Integer::sum)返回的不是int而是Optional<Integer>,以便在空流的情况下安全秩序归约操作
          int tatalCalories4 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
          System.out.println(tatalCalories4);

          int totalCalories5 =menu.stream().mapToInt(Dish::getCalories).sum();
          System.out.println(totalCalories5);
     }

     /**
      * 6.3分组 按菜类型
      */
     @Test
     public void testGoupByType(){
          Map<Dish.Type,List<Dish>> dishesByType =
                  menu.stream().collect(groupingBy(Dish::getType));

          for(Map.Entry<Dish.Type,List<Dish>> typeListEntry:dishesByType.entrySet()){
               System.out.println("键值对：" + typeListEntry);

          }
          System.out.println("----------------a-------------");
          for (List<Dish> list: dishesByType.values()){
               System.out.println("values:" + list);
          }
          System.out.println("------------b----------------");
          for(Dish.Type type:dishesByType.keySet()){
               System.out.println("key: "+type);
               System.out.println("value: " + dishesByType.get(type));
          }
     }

     /**
      * 按卡路里热量分组
      * 低热量 低于400卡路里
      * 普通 处于400~700卡路里
      * 高热量 高于700卡路里
      */
     @Test
     public void testGoupByCalories() {
          Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                  menu.stream().collect(
                          groupingBy(dish -> {
                               if (dish.getCalories() <= 400)
                                    return CaloricLevel.DIET;
                               else if (dish.getCalories() <= 700)
                                    return CaloricLevel.NORMAL;
                               else
                                    return CaloricLevel.FAT;
                          })
                  );

          for (Map.Entry<CaloricLevel, List<Dish>> typeListEntry : dishesByCaloricLevel.entrySet()) {
               System.out.println("键值对：" + typeListEntry);

          }
          System.out.println("----------------a-------------");
          for (List<Dish> list : dishesByCaloricLevel.values()) {
               System.out.println("values:" + list);
          }
          System.out.println("------------b----------------");
          for (CaloricLevel type : dishesByCaloricLevel.keySet()) {
               System.out.println("key: " + type);
               System.out.println("value: " + dishesByCaloricLevel.get(type));
          }


     }

     /**
      * 6.3.1多级分组
      * 低热量 低于400卡路里
      * 普通 处于400~700卡路里
      * 高热量 高于700卡路里
      */
     @Test
     public void testGoupByTypeAndCalories() {
          Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloric =
                  menu.stream().collect(
                          groupingBy(Dish::getType,
                                  groupingBy(dish -> {
                                                 if (dish.getCalories() <= 400)
                                                      return CaloricLevel.DIET;
                                                 else if (dish.getCalories() <= 700)
                                                      return CaloricLevel.NORMAL;
                                                 else
                                                      return CaloricLevel.FAT;
                                  })
                          )
                  );

          for (Map.Entry<Dish.Type,
                  Map<CaloricLevel, List<Dish>>> typeEntry : dishesByTypeCaloric.entrySet()) {
               System.out.println("键值对：" + typeEntry);
          }

          System.out.println("----------------a-------------");
          for (Map<CaloricLevel, List<Dish>> list : dishesByTypeCaloric.values()) {
               System.out.println("values:" + list);
          }

          System.out.println("------------b----------------");
          for (Dish.Type type : dishesByTypeCaloric.keySet()) {
               System.out.println("key: " + type);
               System.out.println("value: " + dishesByTypeCaloric.get(type));
          }

     }

     /**
      * 6.3.2按子组收集数据
      */
     @Test
     public void testGroupByCount() {

          //查询菜单中每类菜的数量
          Map<Dish.Type, Long> typesCount = menu.stream()
                  .collect(groupingBy(Dish::getType, counting()));
          for (Map.Entry<Dish.Type, Long> count : typesCount.entrySet()) {
               System.out.println("键值对：" + count);
          }

          System.out.println("====================================");
          //查询各类菜肴中卡路里最高的菜
          Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                  menu.stream().collect(
                          groupingBy(Dish::getType,
                                  maxBy(comparingInt(Dish::getCalories))));

          for (Map.Entry<Dish.Type, Optional<Dish>> type : mostCaloricByType.entrySet()) {
               System.out.println("键值对：" + type);
          }

     }

     /**
      * 1.把收集器的结果转换为另一种类型
      * 2.与groupingBy联合使用的其他收集器的例子
      */
     @Test
     public void testCollectingAndThen(){
          //查找每个子组中热量最高的Dish
          Map<Dish.Type, Dish> mostCaloricByType2 =
                  menu.stream().collect(
                          groupingBy(Dish::getType,
                                  collectingAndThen(
                                          maxBy(comparingInt(Dish::getCalories)),
                                          Optional::get)
                          )
                  );

          for(Map.Entry<Dish.Type,Dish> type :mostCaloricByType2.entrySet()){
               System.out.println(type);
          }
          System.out.println("=============================");


          //2.与groupingBy联合使用的其他收集器的例子
          //对每组类型菜肴的卡路里求和
          Map<Dish.Type,Integer> totalCaloriesByType =
                  menu.stream().collect(groupingBy(Dish::getType,summingInt(Dish::getCalories)));
          for (Map.Entry<Dish.Type,Integer> sum : totalCaloriesByType.entrySet()){
               System.out.println(sum);
          }

          System.out.println("++++++++++++++++++++++++++++++++++++++++++");

          //每种类型菜肴中，有哪些卡路里热量组成
          Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                  menu.stream().collect(
                          groupingBy(Dish::getType,
                                  mapping(
                                          dish -> {
                                               if (dish.getCalories() <= 400)
                                                    return CaloricLevel.DIET;
                                               else if (dish.getCalories() <= 700)
                                                    return CaloricLevel.NORMAL;
                                               else
                                                    return CaloricLevel.FAT;
                                          },
                                          toSet()
                                  )
                          )
                  );
          for (Map.Entry<Dish.Type,Set<CaloricLevel>> caloricLevel: caloricLevelsByType.entrySet()) {
               System.out.println("键值对：" +caloricLevel);
          }

          System.out.println("---------------------------");
          //toCollection   HashSet
          Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                  menu.stream().collect(
                          groupingBy(Dish::getType,
                                  mapping(
                                          dish -> {
                                               if (dish.getCalories() <= 400)
                                                    return CaloricLevel.DIET;
                                               else if (dish.getCalories() > 400 && dish.getCalories() <= 700)
                                                    return CaloricLevel.NORMAL;
                                               else
                                                    return CaloricLevel.FAT;
                                          },
                                          toCollection(HashSet::new)
                                  )
                          )
                  );

          for (Map.Entry<Dish.Type,Set<CaloricLevel>> caloricLevel: caloricLevelsByType2.entrySet()) {
               System.out.println("键值对：" +caloricLevel);
          }

     }

     /**
      * 6.4分区 由一个谓词（返回一个boolean值）作为分类函数，分区函数
      */
     @Test
     public void testPartition(){
          //菜单按素食和非素食区分
          Map<Boolean,List<Dish>> partitionMenu =
                  menu.stream().collect(partitioningBy(Dish::isVegetarian));

          for (Map.Entry<Boolean,List<Dish>> partion: partitionMenu.entrySet()) {
               System.out.println(partion);
          }

          List<Dish> vegetarianDishes = partitionMenu.get(true);
          /*List<Dish> vegetarianDishes =
                  menu.stream().filter(Dish::isVegetarian).collect(toList());*/

          for (Dish dish: vegetarianDishes) {
               System.out.println(dish);
          }

          System.out.println("-------素食和非素食中的菜肴类型-isVegetarian-------------------");
          //6.4.1 分区产生的素食和非素食子流，分别按类型对菜肴分组
          Map<Boolean,Map<Dish.Type,List<Dish>>>  vegetarianDishesByType =
                  menu.stream().collect(
                          partitioningBy(Dish::isVegetarian,
                                  groupingBy(Dish::getType)));

          for (Map.Entry<Boolean,Map<Dish.Type,List<Dish>>> booleanMapEntry : vegetarianDishesByType.entrySet()){
               System.out.println(booleanMapEntry);
          }
          System.out.println("------max------");
          //素食和非素食热量最高的菜
          Map<Boolean,Dish> mostCaloricPartitionedByVegetarian =
                  menu.stream().collect(
                          partitioningBy(Dish::isVegetarian,
                                         collectingAndThen(
                                                 maxBy(comparingInt(Dish::getCalories)),
                                                 Optional::get
                                         )
                          )
                  );

          for (Map.Entry<Boolean,Dish> mostMax : mostCaloricPartitionedByVegetarian.entrySet()){
               System.out.println(mostMax);
          }
          System.out.println("素食和非素食菜肴各总量：");
          //素食和非素食菜肴数量 counting()
          Map<Boolean,Long> count =
                  menu.stream().collect(
                           partitioningBy(Dish::isVegetarian,counting()));
          for (Map.Entry<Boolean,Long> number : count.entrySet()){
               System.out.println(number);
          }
     }


     /**
      * 自定义收集器 ToListCollector
      */
      @Test
     public void testToListCollector(){
           List<Dish> dishes = menu.stream().collect(new ToListCollector<>());
           dishes.forEach(System.out::println);
           System.out.println("================");

           List<Dish> dishes2 = menu.stream().collect(toList());
           dishes2.forEach(System.out::println);

           System.out.println("------------------");
           List<Dish> dishes3 = menu.stream().collect(
                   ArrayList::new,List::add,List::addAll);
           dishes3.forEach(System.out::println);
      }

     /**
      * 将数字按质数和非质数区分
      */



}
