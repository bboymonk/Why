package com.wjb.demo.threadpoolexecutor;

/**
 * Created by wjb on 2018/1/11.
 */
public class Task implements Runnable {
    private int id;
    private String name;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"执行任务ID"+this.id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
