package com.example.constant;

import com.example.pack.LoginPack;
import com.example.utils.RemotingUtil;
import io.netty.util.AttributeKey;
import io.netty.util.NetUtil;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-19 23:43:00
 */
public interface Constants {

    int MAGIC_NUMBER = 0x12345678;
    int VERSION = 1;
    int HEADER_LENGTH = 16;
    int MAX_FRAME_LENGTH = 1024 * 1024 * 10;
    int LENGTH_FIELD_OFFSET = 4;
    int LENGTH_FIELD_LENGTH = 4;
    int LENGTH_ADJUSTMENT = 0;
    int INITIAL_BYTES_TO_STRIP = 0;

     interface MessageType {
        int LOGIN = 1;
        int LOGOUT = 2;
        int PING = 3;
        int P2P = 4;
    }

    AttributeKey<LoginPack> LOGIN_USER = AttributeKey.valueOf("loginUser");

    AttributeKey<Long> LAST_TIME = AttributeKey.valueOf("lastTime");

    String TOPIC_PREFIX = "MESSAGE:" + RemotingUtil.getLocalAddress() + ":";

    String P2P_SEQUENCE_PREFIX = "p2p_sequence:";

}
