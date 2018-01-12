package com.wjb.demo.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wjb on 2018/1/12.
 */
public class Server {
    private ExecutorService executorService;
    private AsynchronousChannelGroup group;
    public AsynchronousServerSocketChannel channel;

    public Server(int port) {
        try {
            //创建线程池
            executorService = Executors.newCachedThreadPool();
            //通道组的概念，配合线程池使用
            group = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            //创建服务器通道
            channel = AsynchronousServerSocketChannel.open(group);
            channel.bind(new InetSocketAddress(port));
            System.out.println("server start,port is:"+port);
            channel.accept(this,new ServerCompletionHandler());
            //一直阻塞，不让服务器停止。实际不应如此，这里只是为了让线程不终止。
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(8765);
    }

}
