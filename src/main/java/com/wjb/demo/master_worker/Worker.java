package com.wjb.demo.master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wjb on 2018/1/10.
 */
public class Worker implements Runnable{
    private ConcurrentLinkedQueue<Task> queue;
    private ConcurrentHashMap<String,Task> map;

    public void setQueue(ConcurrentLinkedQueue queue){
        this.queue=queue;
    }
    public void setMap(ConcurrentHashMap<String,Task> map){
        this.map=map;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            Task task = this.queue.poll();
            if(task == null){
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.map.put(Integer.toString(task.getId()),task);
        }
    }
}
