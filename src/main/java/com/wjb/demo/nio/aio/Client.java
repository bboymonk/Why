package com.wjb.demo.nio.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * Created by wjb on 2018/1/12.
 */
public class Client implements Runnable {
    private AsynchronousSocketChannel channel;

    public Client() throws IOException {
        channel = AsynchronousSocketChannel.open();
    }

    public void connect(){
        channel.connect(new InetSocketAddress("127.0.0.1",8765));
    }
    public void write(String response){
        try {
            channel.write(ByteBuffer.wrap(response.getBytes())).get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void read(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer).get();
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.out.println(new String(bytes,"utf-8"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //真实开发环境一般是放在一个web容器中的，不会像这样一直死循环的。
    @Override
    public void run() {
        while (true){

        }
    }

    public static void main(String[] args)throws Exception {
        Client c1 = new Client();
        c1.connect();
        Client c2 = new Client();
        c2.connect();
        Client c3 = new Client();
        c3.connect();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c1.write("aaaaa");
        c2.write("bbbbb");
        c3.write("ccccc");

    }
}
