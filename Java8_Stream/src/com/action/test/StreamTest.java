package com.action.test;

import com.action.stream.Trader;
import com.action.stream.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @ClassName : com.action.test.StreamTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-22 17:26
 * @Description :  java 8 in action  第五章测试：
 *     领域：交易员和交易
 */

public class StreamTest {


    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario,2012,700),
            new Transaction(alan,2012,950)
    );

    /**
     * (1)找出2011年发生的所有交易，并按交易额排序（从低到高）
     */
    @Test
    public void test1() {
        List<Transaction> tr2011 =
                transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        tr2011.forEach(System.out::println);
    }

    /**
     * (2)交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test2(){
         /*List<String> cities =
                 transactions.stream()
                 .map(transaction -> transaction.getTrader().getCity())
                 .distinct()
                 .collect(toList());
         cities.forEach(System.out::println);*/
       /* for (String str: cities) {
            System.out.println(str);
        }*/
       Set<String> cities =
               transactions.stream()
               .map(transaction -> transaction.getTrader().getCity())
               .collect(toSet());
       cities.forEach(System.out::println);
    }

    /**
     * (3)查找所有来自于剑桥的交易员，并按姓名排序
     */
    @Test
    public void test3(){
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        traders.forEach(System.out::println);
    }

    /**
     * (4)返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void test4(){
        /*String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1,n2) -> n1 + n2);
        System.out.println(traderStr);*/

        //方法二 使用 joining()
        String traderStr2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        System.out.println(traderStr2);
    }

    /**
     * (5)有没有交易员是在米兰工作的？
     */
    @Test
    public void test5(){
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                                                    .getCity()
                                                    .equals("Milan"));
        if(milanBased == true){
            System.out.println("有交易员是在米兰工作的");
        }else{
            System.out.println("么有交易员是在米兰工作的");

        }
    }

    /**
     * (6)打印生活在剑桥的交易员的所有交易额
     */
    @Test
    public void test6(){
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * (7)所有的交易中，交易额最高的是多少？
     */
    @Test
    public void test7(){
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(maxValue);
    }

    /**
     * (8)找到交易额最小的交易
     */
    @Test
    public void test8(){
        /*Optional<Transaction> minValue = transactions.stream()
                .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(minValue);*/

        Optional<Transaction> minValue2 = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(minValue2);
    }


}
