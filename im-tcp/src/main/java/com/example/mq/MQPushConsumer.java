package com.example.mq;

import com.example.constant.Constants;
import com.example.pack.P2PPack;
import com.example.helper.MessageRouter;
import com.example.session.connect.ConnectionHolder;
import com.example.utils.JsonUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class MQPushConsumer implements MessageListenerConcurrently {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

    static Logger log = org.slf4j.LoggerFactory.getLogger(MQPushConsumer.class);

    @Value("${rocketmq.name-server}")
    private String rocketmqNameServer;

    @Value("${im.server.port:9090}")
    private String port;

    @Autowired
    private ConnectionHolder connectionHolder;

    @Autowired
    MessageRouter messageRouter;

    /**
     * 初始化
     */
    @PostConstruct //java5中引入，指在项目启动的时候执行这个方法
    public void start() {
        try {
            consumer.setNamesrvAddr(rocketmqNameServer);
            consumer.setVipChannelEnabled(false);
            //从消费队列头开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //consumer.setMessageModel(MessageModel.CLUSTERING);
            //订阅主题，仅仅需要订阅当前实例相关的，其它的不关心
            /**
             * 有几个服务，会创建几个topic，发送消息的时候会判断接收人连接的是哪个服务，然后投送到对应的topic
             */
            consumer.subscribe(Constants.TOPIC_PREFIX + port, "*");
            consumer.registerMessageListener(this);
            consumer.start();
            log.info("[启动日志]：MQ消费者已启动");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        int index = 0;
        try {
            for (; index < list.size(); index++) {
                MessageExt msg = list.get(index);
                P2PPack p2PPack = JsonUtils.fromJsonByte(msg.getBody(), P2PPack.class);
                log.info("MQ:消费者接受新消息：{}{}{}{}{}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), p2PPack);
                boolean success = messageRouter.route(p2PPack);
                if (!success) {
                    log.info("更新消息状态为NO_ROUTE");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (index < list.size()) {
                consumeConcurrentlyContext.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy //在关闭spring容器后释放一些资源*
    public void stop() {
        if (null != consumer) {
            consumer.shutdown();
            log.error("MQ: 关闭消费者");
        }
    }
}
 
 
 