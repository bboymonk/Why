package com.wjb.demo.delayblockingqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjb on 2018/1/10.
 *  网 民
 */
public class Person implements Delayed{
    private int id;
    private String name;
    //下机时间
    private long endTime;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Person(int id, String name, long endTime) {
        this.id = id;
        this.name = name;
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        return this.getDelay(this.timeUnit) - ((Person)delayed).getDelay(this.timeUnit) > 0 ? 1 : 0;
    }
}
