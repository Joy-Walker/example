package com.example.server.task;

import com.example.constant.Constants;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:34:00
 */
public class TaskFactory {
    public static Task getTask(int messageType) {
        switch (messageType) {
            case Constants.MessageType.LOGIN:
                return new LoginTask();
            case Constants.MessageType.LOGOUT:
                return new LogoutTask();
            case Constants.MessageType.PING:
                return new PingTask();
            case Constants.MessageType.P2P:
                return new P2PTask();
            case Constants.MessageType.MESSAGE_ACK:
                return new MessageAckTask();
            default:
                throw new RuntimeException("messageTyp:" + messageType + " is illegal");
        }
    }
}
