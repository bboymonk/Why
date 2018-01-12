package com.wjb.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by wjb on 2018/1/12.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //具体处理交给ClientHandler
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture connect = b.connect("127.0.0.1", 8888);
        System.out.println("client connected");
        connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello server 1".getBytes()));
        Thread.sleep(1000);
        connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello server 2".getBytes()));
        Thread.sleep(1000);
        connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello server 3".getBytes()));

        connect.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

}
