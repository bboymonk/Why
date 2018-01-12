package com.wjb.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wjb on 2018/1/12.
 */
public class ServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 为什么客户端有 ReferenceCountUtil.release(msg)释放，而服务器没有呢。
     * 因为服务器端只要有写操作就会自动释放，如果只有读没有写的话也要手动释放。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf缓冲区引用计数对象，无须像NIO一样flip，因为它内部有两个指针，分别处理读和写。
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        //从缓冲区把数据读到新建的数组里
        buf.readBytes(bytes);
        System.out.println(new String(bytes,"utf-8"));
        String response = "hello client";
        //通过工具类把数据写到缓冲区
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));

    }
}
