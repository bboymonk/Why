package com.wjb.demo.master_worker;

import java.util.Random;

/**
 * Created by wjb on 2018/1/10.
 * 多线程master-workersa模式
 */
public class Main {
    public static void main(String[] args) {
        Master master = new Master(new Worker(), 10);

        Random random = new Random();
        for(int i = 0;i<100;i++){
            Task task = new Task();
            task.setId(i);
            task.setPrice(random.nextInt());
            master.submit(task);
        }
        master.execute();
        long start = System.currentTimeMillis();
        while (true){
            if (master.isComplete()){
                long end = System.currentTimeMillis();
                System.out.println("耗时："+(end-start));
                break;
            }
        }




    }
}
