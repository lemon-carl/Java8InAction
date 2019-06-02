package com.java.thread;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @ClassName : SwingAndThread
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-11-03 13:16
 * @Description : 线程实现
 * 实现图标移动的功能
 */
public class SwingAndThread extends JFrame{

    private  JLabel jLabel = new JLabel();
    private static Thread thread;
    private int count = 0;
    private Container container = getContentPane();

    public SwingAndThread(){
        setBounds(300,200,250,100);
        container.setLayout(null);
        URL url =SwingAndThread.class.getResource("./lib/Yuki Uchida.jpg");
        Icon icon = new ImageIcon(url);
        jLabel.setIcon(icon);
        jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel.setBounds(10,10,511,512);
        jLabel.setOpaque(true);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= 200){
                    jLabel.setBounds(count,10,511,512);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count += 4;
                    if (count == 400){
                        count = 10;
                    }
                }
            }
        });
        thread.start();
        container.add(jLabel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {
        new SwingAndThread();
    }
}
