package com.wjb.demo.futuredemo;

/**
 * Created by wjb on 2018/1/10.
 */
public class FutureClient {
    public Data request(String queryString) {
        //返回futureData包装类，此时暂无数据
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(queryString);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }
}
