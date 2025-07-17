package com.example.server.task;

import com.example.message.Message;
import com.example.pack.MessageAckPack;
import com.example.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :panligang
 * @description :
 * @create :2025-07-16 17:18:00
 */
public class MessageAckTask implements Task {

    static Logger logger = LoggerFactory.getLogger(MessageAckTask.class);

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        MessageAckPack messageAckPack = JsonUtils.fromJsonByte(message.getBody(), MessageAckPack.class);
        logger.info("MessageAckPack:{}",messageAckPack);
        messageAckPack.setMessageType(message.getHeader().getMessageType());
        logger.info("更新消息状态，为已收到");
    }
}
