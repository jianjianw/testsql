package com.nhsoft.pattern;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
        apple01.setWeight(20);
        Apple apple02 = new Apple();
        apple02.setColor("red");
        apple02.setWeight(20);
        Apple apple03 = new Apple();
        apple03.setColor("red");
        apple03.setWeight(20);
        inventory.add(apple01);
        inventory.add(apple02);
        inventory.add(apple03);
        //List<Apple> apples = filterApples(inventory, (Apple a ) -> a.getColor().equals("green"));
        //inventory.sort(Comparator.comparing(Apple::getColor).);
        System.out.println();

       /* inventory.removeIf((Apple a) -> a.getColor().equals("red"));
        System.out.println();*/

    }

    @Test
    public void test(){

        String str = "123456789";
        String substring = str.substring(str.length() - 6);
        System.out.println(substring);
    }


    @Test
    public void test1(){




        String utcTime = "2018-01-31T14:32:19Z";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date after = null;
        try {
            after = df.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(after);
    }

    @Test
    public void test02(){
        String value01 = "2019-04-01T16:00:00.000Z";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = null;
        try {
            parse = sdf.parse(value01);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    @Test
    public void test03(){

        Date date = new Date();
        System.out.println(date);
    }





}
