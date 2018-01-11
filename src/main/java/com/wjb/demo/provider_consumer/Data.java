package com.wjb.demo.provider_consumer;

/**
 * Created by wjb on 2018/1/11.
 */
public class Data {
    private int id;
    private String name;
    public Data(int id,String name){
        this.id=id;
        this.name=name;
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
}
