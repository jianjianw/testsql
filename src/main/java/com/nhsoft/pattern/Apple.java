package com.nhsoft.pattern;

public class Apple {

    private String Color;
    private int Weight;

    public static boolean isGreenApple( Apple apple) { return "green". equals( apple. getColor()); }
    public static boolean isHeavyApple( Apple apple) { return apple. getWeight() > 150; }


    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }
}
