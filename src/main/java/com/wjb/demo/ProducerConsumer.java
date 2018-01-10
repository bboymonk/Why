package com.wjb.demo;

/**
 * Created by wjb on 2018/1/9.
 * 多线程之生产者消费者，wait notify使用
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        AppleQueue queue = new AppleQueue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Apple {
        int data;

        public Apple(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "data=" + data +
                    '}';
        }
    }

    static class AppleQueue {
        int index;
        Apple[] apples = new Apple[10];

        public synchronized void push(Apple apple) {
            if (index == apples.length) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notify();
            apples[index] = apple;
            index++;
        }

        public synchronized Apple pull() {
            while (index == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notify();
            index--;
            return apples[index];
        }


    }

    static class Producer implements Runnable {
        AppleQueue queue;

        public Producer(AppleQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                Apple apple = new Apple(i);
                queue.push(apple);
                System.out.println("生产了：" + apple);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        AppleQueue queue;

        public Consumer(AppleQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                Apple apple = queue.pull();
                System.out.println("消费了：" + apple);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
