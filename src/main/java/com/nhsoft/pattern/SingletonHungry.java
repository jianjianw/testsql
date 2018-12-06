package com.nhsoft.pattern;


/**
 * 单例模式  饿汉式
 */
public class SingletonHungry {

    private SingletonHungry (){
    }
    private static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance(){
        return instance;
    }

}
