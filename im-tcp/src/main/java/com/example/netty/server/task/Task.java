package com.example.netty.server.task;

import com.example.message.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:29:00
 */
public interface Task {

    public void run(ChannelHandlerContext ctx, Message message);
}
