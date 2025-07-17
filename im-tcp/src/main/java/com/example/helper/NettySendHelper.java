package com.example.helper;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author :panligang
 * @description :
 * @create :2025-07-16 16:55:00
 */
public class NettySendHelper {

    public static void sendMessage(ChannelHandlerContext ctx, Object msg)  {
        ctx.writeAndFlush(msg);
    }

    public static void sendMessageAndListener(ChannelHandlerContext ctx, Object msg, ChannelFutureListener listener)  {
        ctx.writeAndFlush(msg).addListener( listener);
    }

}
