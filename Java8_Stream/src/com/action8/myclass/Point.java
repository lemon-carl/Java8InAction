package com.action8.myclass;

import static java.util.Comparator.comparing;

/**
 * @ClassName : Point
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-28 15:03
 * @Description : 图形应用
 */
public class Point {



    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Point moveRightBy(int x){
        return new Point(this.x + x , y);
    }


}
