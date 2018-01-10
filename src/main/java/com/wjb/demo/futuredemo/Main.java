package com.wjb.demo.futuredemo;

/**
 * Created by wjb on 2018/1/10.
 * 多线程Future模式 示例
 */
public class Main {

    public static void main(String[] args) {
        FutureClient client = new FutureClient();
        //这里返回的暂时是空数据
        Data data = client.request("查询请求");
        System.out.println("请求发送成功");
        System.out.println("数据正在处理中，此时可以做其它操作");
        String result = data.getRequest();
        System.out.println(result);
    }
}
