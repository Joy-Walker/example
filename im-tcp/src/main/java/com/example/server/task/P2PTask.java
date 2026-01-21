package com.example.server.task;

import com.example.message.Message;
import com.example.model.Result;
import com.example.pack.P2PPack;
import com.example.service.MessageService;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author :panligang
 * @description : 单聊
 * @create :2024-06-12 20:06:00
 */
public class P2PTask implements Task {

    private final MessageService messageService;

    public P2PTask() {
        messageService = SpringContext.getBean(MessageService.class);
    }

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        P2PPack p2PPack = JsonUtils.fromJsonByte(message.getBody(), P2PPack.class);
        p2PPack.setMessageType(message.getHeader().getMessageType());
        //1、调用业务逻辑层
        Result result = messageService.P2PMessage(p2PPack);
        //2、向客户端写会ack
        ctx.writeAndFlush(result);
    }
}
