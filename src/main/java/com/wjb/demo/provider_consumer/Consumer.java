package com.wjb.demo.provider_consumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by wjb on 2018/1/11.
 */
public class Consumer implements Runnable{
    private BlockingQueue<Data> queue;
    private volatile boolean isRunning = true;
    public Consumer(BlockingQueue queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Data data = this.queue.take();
                Thread.sleep(1000);
                System.out.println("消费的数据ID是："+data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
