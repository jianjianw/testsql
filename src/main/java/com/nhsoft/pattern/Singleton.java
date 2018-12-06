package com.nhsoft.pattern;

/**
 *  单例模式  懒汉式
 */
public class Singleton {

    private static class SingletonHolder{
        private static Singleton instance = new Singleton();
    }

    private Singleton(){
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
