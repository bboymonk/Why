package com.wjb.demo.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjb on 2018/1/11.
 * 自定义线程池,有界队列的使用策略
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

        /**
         * 首先任务1进来被一个线程执行，任务2，3，4进来时会暂存到队列里去，任务5进来时，
         * 队列已无法暂存，如果当前线程数小于最大线程数，则创建新线程执行此任务。
         * 所以任务5进来时会新建一个线程执行。
         * 如果还有任务6进来的话，此时会执行拒绝策略，JDK默认是AbortPolicy,这里是自定义拒绝策略。
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),new MyRejected());

        Task task1 = new Task(1, "任务1");
        Task task2 = new Task(2, "任务2");
        Task task3 = new Task(3, "任务3");
        Task task4 = new Task(4, "任务4");
        Task task5 = new Task(5, "任务5");
        Task task6 = new Task(6, "任务6");

        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.execute(task4);
        pool.execute(task5);
        pool.execute(task6);

        pool.shutdown();


    }
}
