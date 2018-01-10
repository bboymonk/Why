package com.wjb.demo.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by wjb on 2018/1/10.
 * 延迟队列DelayQueue的示例
 *  网 吧
 */
public class DelayQueueDemo implements Runnable {

    private DelayQueue<Person> queue = new DelayQueue();

    /**
     * 网吧交钱上机
     */
    public void work(String name, int id, int money) {
        Person person = new Person(id, name, 1000 * money + System.currentTimeMillis());
        System.out.println(name+"上机"+money);
        this.queue.add(person);
    }
    public void stop(Person person){
        System.out.println(person.getName()+"下机");
    }


    public static void main(String[] args) {
        DelayQueueDemo delayQueueDemo = new DelayQueueDemo();
        Thread thread = new Thread(delayQueueDemo);
        thread.start();

        delayQueueDemo.work("a",123,1);
        delayQueueDemo.work("b",456,5);
        delayQueueDemo.work("c",789,10);
    }


    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            try {
                Person person = this.queue.take();
                stop(person);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (queue.isEmpty()){
                    flag = false;
                }
            }
        }
    }
}
