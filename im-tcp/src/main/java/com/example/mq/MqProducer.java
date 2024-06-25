package com.example.mq;

import com.example.constant.Constants;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-25 18:56:00
 */
@Component
public class MqProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${im.server.port}")
    private String port;

    public SendResult sendMsg(String topic, Object msg) {
        return this.rocketMQTemplate.syncSend(topic, msg);
    }

}
