package com.nhsoft.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Lam {



    static List< Apple> filterApples(List< Apple> inventory, Predicate< Apple> p){

        List< Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test( apple)){
                result. add( apple);
            }

        }
        return result;
    }

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        Apple apple01 = new Apple();
        apple01.setColor("green");
        Apple apple02 = new Apple();
        apple02.setColor("red");
        Apple apple03 = new Apple();
        apple03.setColor("red");
        inventory.add(apple01);
        inventory.add(apple02);
        inventory.add(apple03);
//        List<Apple> apples = filterApples(inventory, (Apple a ) -> a.getColor().equals("green"));
//        System.out.println();

        inventory.removeIf((Apple a) -> a.getColor().equals("red"));
        System.out.println();

    }




}
