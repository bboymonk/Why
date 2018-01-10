package com.wjb.demo;

/**
 * Created by wjb on 2018/1/9.
 * 线程安全的单例
 */
public class SingletonDemo {

    public static class InnerSingleton{
        private static SingletonDemo instance = new SingletonDemo();
    }

    public SingletonDemo getInstance(){
        return InnerSingleton.instance;
    }
}
