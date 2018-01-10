package com.wjb.demo.futuredemo;

/**
 * Created by wjb on 2018/1/10.
 */
public class FutureData implements Data {
    private RealData realData;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        //如果已经装载完毕直接返回
        if (isReady) {
            return;
        }
        this.realData=realData;
        isReady=true;
        notify();
    }


    @Override
    public synchronized String getRequest() {
        //如果没有装载好，一直阻塞
        while (!isReady){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果装载好了返回真实数据,这里调用的是真实数据realData的getRequest()方法。
        return this.realData.getRequest();
    }
}
