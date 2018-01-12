package com.wjb.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by wjb on 2018/1/12.
 * Selector和Channel这两个概念和关系，Selector里有很多Channel，
 * Selector会根据key来轮询通道
 *
 */
public class Server implements Runnable {
    private Selector selector;
    private ByteBuffer readBuf = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuf = ByteBuffer.allocate(1024);
    public Server(int port){
        try {
            //打开多路复用器
            this.selector = Selector.open();
            //打开服务器通道
            ServerSocketChannel channel = ServerSocketChannel.open();
            //设置为非阻塞模式
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(port));
            //把通道注册到Selector上，并监听阻塞事件
            channel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start,port is :"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                //Selector开始监听
                this.selector.select();
                //返回Selector选择的结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    //key其实就是通道的key，意思就是选择了一个通道
                    SelectionKey key = keys.next();
                    //把选择的这个key从所有的keys中移除，避免重复执行
                    keys.remove();
                    //如果key是有效的
                    if (key.isValid()){
                        //如果为阻塞状态
                        if (key.isAcceptable()){
                            this.accept(key);
                        }
                        //如果是可读状态
                        if(key.isReadable()){
                            this.read(key);
                        }
                        //如果是可写状态
                        if (key.isWritable()){

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key){
        //清空旧数据
        this.readBuf.clear();
        //获取注册的通道channel
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            int count = channel.read(this.readBuf);
            if (count == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            //如果有数据就读取，别忘了复位
            this.readBuf.flip();
            //remainint()方法可获取缓冲取可读数据
            byte[] bytes = new byte[this.readBuf.remaining()];
            //接收缓冲区数据
            this.readBuf.get(bytes);

            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void accept(SelectionKey key){
        //根据key获取当前通道
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try {
            SocketChannel channel = ssc.accept();
            channel.configureBlocking(false);
            channel.register(this.selector,SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8888)).start();
    }



}
