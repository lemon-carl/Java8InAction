package com.java.thread;

/**
 * @ClassName : TreadSafeTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-11-03 15:25
 * @Description : 实现模拟火车站售票进行的功能
 */
public class TreadSafeTest implements Runnable{

    int num = 10;


    @Override
    public void run() {
        while (true){
            synchronized ("") {
                if (num > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("tickets: " + num--);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreadSafeTest test = new TreadSafeTest();
        Thread tA = new Thread(test);
        Thread tB = new Thread(test);
        Thread tC = new Thread(test);
        Thread tD = new Thread(test);

        tA.start();
        tB.start();
        tC.start();
        tD.start();
    }
}
