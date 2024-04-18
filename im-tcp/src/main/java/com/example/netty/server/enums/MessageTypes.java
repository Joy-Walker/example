package com.example.netty.server.enums;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 20:44:00
 */
public enum MessageTypes {

    HEART_BEAT(1),
    LOGIN(2),
    LOGIN_RESP(3),
    MESSAGE_REQUEST(4),
    MESSAGE_RESPONSE(5),
    CREATE_GROUP_REQUEST(6),
    CREATE_GROUP_RESPONSE(7),
    JOIN_GROUP_REQUEST(8),
    JOIN_GROUP_RESPONSE(9),
    QUIT_GROUP_REQUEST(10),
    QUIT_GROUP_RESPONSE(11),

    LIST_GROUP_MEMBERS_REQUEST(12),
    LIST_GROUP_MEMBERS_RESPONSE(13),
    GROUP_MESSAGE_REQUEST(14),
    GROUP_MESSAGE_RESPONSE(15),

    LOGOUT_REQUEST(16),
    LOGOUT_RESPONSE(17),
    ;
    private int value;
    private MessageTypes(int value) {
        this.value = value;
    }


}
