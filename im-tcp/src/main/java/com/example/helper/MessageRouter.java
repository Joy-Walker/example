package com.example.helper;

import com.example.model.Result;
import com.example.mq.MqProducer;
import com.example.pack.Basepack;
import com.example.service.MessageService;
import com.example.session.SessionRegistry;
import com.example.session.connect.ConnectionHolder;
import io.netty.channel.Channel;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2025-07-16 16:21:00
 */
@Component
public class MessageRouter {

    Logger LOGGER = LoggerFactory.getLogger(MessageRouter.class);

    @Autowired
    private ConnectionHolder connectionHolder;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    MqProducer mqProducer;

    @Autowired
    MessageService messageService;


    public boolean route(Basepack pack) {
        Channel channel = connectionHolder.getConnection(String.valueOf(pack.getToId()));
        if (channel != null) {
            channel.writeAndFlush(Result.success("p2pMessage", pack)).addListener(future -> {
                if (future.isSuccess()) {
                    LOGGER.info("更新消息状态为SENT");
                } else {
                    LOGGER.info("更新消息状态为FAIL");
                }
            });
            return true;
        }
        String topic = sessionRegistry.getTopic(String.valueOf(pack.getToId()));
        if (topic != null) {
            SendResult result = mqProducer.sendMsg(topic, pack);
            return result != null && result.getSendStatus() == SendStatus.SEND_OK;
        }
        return false; // NO_ROUTE
    }

}
