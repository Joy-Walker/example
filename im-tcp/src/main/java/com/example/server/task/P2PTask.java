package com.example.server.task;

import com.example.message.Message;
import com.example.pack.P2PPack;
import com.example.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author :panligang
 * @description : 单聊
 * @create :2024-06-12 20:06:00
 */
public class P2PTask implements Task {


    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        P2PPack p2PPack = JsonUtils.fromJsonByte(message.getBody(), P2PPack.class);
        p2PPack.setMessageType(message.getHeader().getMessageType());
        //1、调用业务逻辑层
        //2、写会ack
    }
}
