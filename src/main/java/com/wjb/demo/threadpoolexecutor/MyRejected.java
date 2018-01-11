package com.wjb.demo.threadpoolexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wjb on 2018/1/11.
 * 自定义拒绝策略，这里可以自行处理，真实场景中，数据是不能丢失的，
 * 所以放在缓存中或是存入数据库或者定时任务什么的都行。
 */
public class MyRejected implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString());
    }
}
