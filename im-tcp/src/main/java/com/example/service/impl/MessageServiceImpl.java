package com.example.service.impl;

import com.example.model.Result;
import com.example.pack.P2PPack;
import com.example.service.MessageService;
import com.example.service.SequenceService;
import com.example.session.SessionRegistry;
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

    @Override
    public Result P2PMessage(P2PPack pack) {
        // 1、校验好友关系，黑名单关系
        LOGGER.info("1、校验好友关系，黑名单关系");
        Long formId = pack.getFormId();
        Long toId = pack.getToId();
        if(formId == null || toId == null) {
            return Result.fail("参数错误");
        }
        // 2、消息幂等处理
        String messageKey = pack.getMessageKey();
        LOGGER.info("2、消息幂等处理：{}",messageKey);
        // 3、为消息生成seq用于排序
        long seq = sequenceService.getSequence(P2P_SEQUENCE_PREFIX + formId + ":" );
        LOGGER.info("3、为消息生成seq用于排序：{}",seq);
        // 4、落库[写扩散逻辑]
        LOGGER.info("4、消息落库：{}",messageKey);
        // 5、发送消息[给对端发]
        String topic = sessionRegistry.getTopic(String.valueOf(toId));
        LOGGER.info("5、发送消息到topic：{}",topic);
        return Result.success("ack:" + messageKey);
    }
}
