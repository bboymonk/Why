package com.wjb.demo.nio.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by wjb on 2018/1/12.
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
    /**
     * 类似于递归，或者叫拉力，反复调用Server里的accept()方法，保证多个客户端都能连上
     * 因为AIO不像NIO是一直轮询的。
     *
     * @param channel
     * @param server
     */
    @Override
    public void completed(AsynchronousSocketChannel channel, Server server) {
        server.channel.accept(server, this);
        read(channel);
    }

    @Override
    public void failed(Throwable exc, Server server) {
        exc.printStackTrace();
    }

    public void read(final AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                String s = new String(attachment.array());
                System.out.println("server response data is :" + s);
                write(channel, s);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    public void write(AsynchronousSocketChannel channel, String data) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(data.getBytes());
            buffer.flip();
            channel.write(buffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
