package com.wjb.demo.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wjb on 2018/1/10.
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        Task task1 = new Task();
        task1.setId(1);
        task1.setName("id为1");
        Task task2 = new Task();
        task2.setId(5);
        task2.setName("id为5");
        Task task3 = new Task();
        task3.setId(2);
        task3.setName("id为2");

        queue.add(task1);
        queue.add(task2);
        queue.add(task3);

        System.out.println(queue.take().getId());
        System.out.println(queue.take().getId());
        System.out.println(queue.take().getId());



    }


}
