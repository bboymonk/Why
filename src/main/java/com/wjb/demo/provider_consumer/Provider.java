package com.wjb.demo.provider_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wjb on 2018/1/11.
 */
public class Provider implements Runnable{
    private BlockingQueue queue;
    private volatile boolean isRunning = true;
    private static AtomicInteger count =  new AtomicInteger();

    public Provider(BlockingQueue queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(1000);
                int id = count.incrementAndGet();
                Data data = new Data(id, Integer.toString(id));
                System.out.println("当前线程："+Thread.currentThread().getName()+"生产数据ID是："+id);
                if (!this.queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("数据放入队列失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isRunning=false;
    }


}
