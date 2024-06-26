package com.example.server.task;

import com.example.message.Message;
import com.example.model.Result;
import com.example.pack.PingPack;
import com.example.session.SessionRegistry;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.constant.Constants.LAST_TIME;
import static com.example.constant.Constants.TOPIC_PREFIX;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 20:05:00
 */
public class PingTask implements Task{


    private SessionRegistry sessionRegistry;
    public PingTask() {
        sessionRegistry = SpringContext.getBean(SessionRegistry.class);
    }
    @Override
    public void run(ChannelHandlerContext ctx, Message message) {

        PingPack pingPack = JsonUtils.fromJsonByte(message.getBody(), PingPack.class);
        pingPack.setMessageType(message.getHeader().getMessageType());
        sessionRegistry.register(pingPack.getUserId(),TOPIC_PREFIX );
        ctx.channel().attr(LAST_TIME).set(System.currentTimeMillis());
        ctx.writeAndFlush(Result.success(System.currentTimeMillis()));
    }
}
