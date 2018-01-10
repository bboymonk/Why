package com.wjb.demo.futuredemo;

/**
 * Created by wjb on 2018/1/10.
 * 真实数据类
 */
public class RealData implements Data {
    private String result;
    public RealData(String queryString){
        System.out.println("根据"+queryString+"查询操作，一系列的操作省略。。。" );
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完成");
        result="查询结果";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
