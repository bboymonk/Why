package com.wjb.demo.nio;

import java.nio.IntBuffer;

/**
 * Created by wjb on 2018/1/12.
 * Buffer对象使用，有很多个Buffer抽象类，实际上是一个数组，对应JAVA的基本数据类型，除了boolean。
 * Buffer对象有几个属性 position,limit,capacity
 */
public class BufferDemo {
    public static void main(String[] args) {
        //分配10个大小的容量，此时buffer是 [pos=0 lim=10 cap=10]
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        //put了三个元素，此时buffer是 [pos=3 lim=10 cap=10]
        System.out.println(buffer);
        /**
         * get()无参数方法获取position位置元素，此时position为3，无元素，所以返回0，并且position加1。
         * 有参数就获取指定下标的元素。
         * 此时buffer是 [pos=4 lim=10 cap=10]
         */
        System.out.println(buffer.get());
        buffer.flip();
        /**
         *
         * flip()是复位的意思，此时buffer是 [pos=0 lim=4 cap=10]
         *一般需要读取buffer中的元素时，必须调用flip()方法,否则异常。
         * filp()方法后，position变成0，limit变为4（limit可以理解成buffer中实际存了多少元素的容器）
         */
        System.out.println(buffer);
        for (int i = 0;i<buffer.limit();i++){
            System.out.println(buffer.get());
        }
//        System.out.println(buffer.capacity());
//        System.out.println(buffer.limit());

        System.out.println(buffer);
        System.out.println(buffer.get());


    }

    /**
     * 其它的一些方法使用
     */
    public void wrapDemo(){
        int[] array = new int[]{1,3,5};
        //wrap()方法可以包裹一个数组成来Buffer对象
        IntBuffer wrap = IntBuffer.wrap(array);
        //只要1和3
        IntBuffer.wrap(array,0,2);
        IntBuffer buffer = IntBuffer.allocate(10);
        //复制一份buffer
        IntBuffer buffer2 = buffer.duplicate();
    }




}
