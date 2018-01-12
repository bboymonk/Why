package com.wjb.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * Created by wjb on 2018/1/12.
 */
public class Client {
    public static void main(String[] args) {
        SocketChannel channel =null;
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel = SocketChannel.open();
            channel.connect(address);
            while (true){
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                buffer.put(bytes);
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}
