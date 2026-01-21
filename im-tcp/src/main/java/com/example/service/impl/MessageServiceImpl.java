package com.example.service.impl;

import com.example.helper.IdempotencyService;
import com.example.helper.ValidatePackService;
import com.example.model.Result;
import com.example.mq.MqProducer;
import com.example.pack.Basepack;
import com.example.pack.P2PPack;
import com.example.service.MessageService;
import com.example.service.SequenceService;
import com.example.session.SessionRegistry;
import com.example.utils.AckUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.constant.Constants.P2P_SEQUENCE_PREFIX;


@Service
public class MessageServiceImpl implements MessageService {

    static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    SequenceService sequenceService;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    MqProducer mqProducer;

    @Autowired
    IdempotencyService idempotencyService;

    @Override
    public Result P2PMessage(P2PPack pack) {
        if (!ValidatePackService.validatePack(pack)) {
            return AckUtils.fail(pack.getMessageKey(), "参数错误");
        }
        String messageKey = pack.getMessageKey();
        if (!idempotencyService.checkAndSet(messageKey)) {
            return AckUtils.ack(messageKey);
        }
        generateAndSetSequence(pack);

        if (!persistMessage(pack)) {
            return AckUtils.fail(messageKey, "消息持久化失败");
        }

        String topic = sessionRegistry.getTopic(String.valueOf(pack.getToId()));

        if (topic == null) {
            LOGGER.info("更新消息状态为NO_ROUTE");
            return AckUtils.ack(messageKey);
        }

        SendResult sendResult = mqProducer.sendMsg(topic, pack);
        if (sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK) {
            LOGGER.info("6、更新消息状态为SENT");
            return Result.success(messageKey);
        }
        LOGGER.info("7、更新消息状态为SEND_FAIL");
        /**
         * send fail可以快速重试，其实底层会重试的，但是为了简单，这里就不处理了
         * 实在失败的不行，最终让客户端重发
         */
        return AckUtils.fail(messageKey, "send fail");
    }

    private boolean persistMessage(Basepack pack) {



        return true;
    }

    private void generateAndSetSequence(P2PPack pack) {
        long seq = sequenceService.getSequence(P2P_SEQUENCE_PREFIX + pack.getFormId() + ":");
        pack.setSequenceId(seq);
    }


    @Override
    public Result pullOfflineMessage(String userId) {
        return null;
    }
}
