package com.example.server.task;

import com.example.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import static com.example.constant.Constants.LAST_TIME;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 20:05:00
 */
public class PingTask implements Task{
    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        ctx.channel().attr(LAST_TIME).set(System.currentTimeMillis());
        ctx.writeAndFlush(new PingTask());
    }
}
