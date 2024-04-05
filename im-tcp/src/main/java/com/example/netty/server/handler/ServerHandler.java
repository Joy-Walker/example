package com.example.netty.server.handler;

import com.example.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 14:51:00
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println(msg);
    }
}
