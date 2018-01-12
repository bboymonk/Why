package com.wjb.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by wjb on 2018/1/12.
 */
public class Server {
    public static void main(String[] args) throws Exception{
        //处理客户端连接
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        //处理网络读写
        NioEventLoopGroup cGroup = new NioEventLoopGroup();
        //辅助工具类，用于配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup,cGroup)
                //指定NIO模式
                .channel(NioServerSocketChannel.class)
                //tcp缓冲区大小
                .option(ChannelOption.SO_BACKLOG,1024)
                //发送缓冲大小
                .option(ChannelOption.SO_SNDBUF,32*1024)
                //接收缓冲大小
                .option(ChannelOption.SO_RCVBUF,32*1024)
                //保持连接
                .option(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //具体处理交给ServerHandler
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture future = bootstrap.bind(8888).sync();
        System.out.println("server connected");
        //阻塞等待,类似Thread.sleep(Integer.MAX_VALUE)，实际开发不这样
        future.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();

    }

}
