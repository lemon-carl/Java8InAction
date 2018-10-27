package com.action6.stream;

/**
 * @ClassName : Dish
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-24 14:36
 * @Description : 菜肴类
 */
public class Dish {

    //枚举， 肉，鱼，其他
    public enum Type {MEAT,FISH,OTHER}
    //菜名，是否素食，卡路里，类型
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type  getType(){
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}

