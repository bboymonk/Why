package com.wjb.demo.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wjb on 2018/1/11.
 * 线程池 使用无界队列的策略
 */
public class ThreadPoolExecutor2 implements Runnable{
    private int id;
    private String name;
    private static AtomicInteger count = new AtomicInteger();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            int id = count.incrementAndGet();
            System.out.println(Thread.currentThread().getName()+"执行的任务ID："+id);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        /**
         * 来一个任务新建一个线程，当线程到corePoolSize时不再创建线程，所以这里的最大线程数一般和核心线程一样。
         * 所有新来的任务都暂存到队列中
         * 无界队列不会拒绝新来的任务直到内存耗净。
         */
        ExecutorService executor = new ThreadPoolExecutor(5,5,120, TimeUnit.SECONDS,queue);

        for (int i = 1;i<= 20;i++){
            executor.execute(new ThreadPoolExecutor2());
        }
        try {
            Thread.sleep(1000);
            System.out.println("队列大小"+queue.size());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

}
