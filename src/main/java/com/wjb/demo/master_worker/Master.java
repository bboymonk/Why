package com.wjb.demo.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wjb on 2018/1/10.
 */
public class Master {
    //需要一个装任务的容器，这里使用非阻塞队列
    private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue();
    //需要一个装worker的容器
    private HashMap<String,Thread> map = new HashMap<String,Thread>();
    //需要一个容器放所有worker处理完的数据
    private ConcurrentHashMap<String,Task> resultMap = new ConcurrentHashMap<String,Task>();

    public Master(Worker worker,int count){
        worker.setMap(resultMap);
        worker.setQueue(queue);
        for(int i =0;i<count;i++){
            this.map.put(Integer.toString(i),new Thread(worker));
        }
    }
    public void submit(Task task){
        this.queue.add(task);
    }

    public void execute(){
        for (Map.Entry<String,Thread> m:map.entrySet()){
            m.getValue().start();
        }
    }

    public boolean isComplete(){
        for (Map.Entry<String,Thread> m:map.entrySet()){
            if (m.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

}
