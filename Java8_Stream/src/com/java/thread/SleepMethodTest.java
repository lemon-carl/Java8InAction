package com.java.thread;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @ClassName : SleepMethodTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-11-03 12:49
 * @Description :  线程的休眠
 * 实现在窗体中自动画线段的功能，并且为线段设置颜色，颜色随机产生
 */
public class SleepMethodTest extends JFrame{

    private Thread thread;

    private static Color [] colors =
            {Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.ORANGE,
             Color.YELLOW,Color.RED,Color.PINK,Color.LIGHT_GRAY};

    private static final Random RANDOM = new Random();

    private  static Color getC(){
        return colors[RANDOM.nextInt(colors.length)];
    }

    public SleepMethodTest() {
        thread = new Thread(new Runnable() {
            int x = 100;
            int y = 80;

            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Graphics graphics = getGraphics();
                    graphics.setColor(getC());
                    graphics.drawLine(x,y,180,y++);
                    if (y >= 120){
                        y = 80;
                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        init(new SleepMethodTest(),100,100);
    }

    public static void init(JFrame jFrame, int width, int height) {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width,height);
        jFrame.setVisible(true);
    }

}
