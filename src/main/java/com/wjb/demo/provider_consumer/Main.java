package com.wjb.demo.provider_consumer;

import java.util.concurrent.*;

/**
 * Created by wjb on 2018/1/11.
 */
public class Main {
    public static void main(String[] args) {
        //无界的阻塞队列，个数无限制
        BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>();

        //缓存线程池，可以创建无限大数量的线程，没有任务时不创建线程，空闲线程存活时间默认60S
        ExecutorService threadPool = Executors.newCachedThreadPool();

        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);

        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        threadPool.execute(p1);
        threadPool.execute(p2);
        threadPool.execute(p3);
        threadPool.execute(c1);
        threadPool.execute(c2);
        threadPool.execute(c3);
        try {
            Thread.sleep(3000);
            p1.stop();
            p2.stop();
            p3.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}
