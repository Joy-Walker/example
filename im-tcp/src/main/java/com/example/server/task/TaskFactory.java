package com.example.server.task;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:34:00
 */
public class TaskFactory {
    public static Task getTask(int messageType) {
        switch (messageType) {
            case 1:
                return new LoginTask();
            case 2:
                return new LogoutTask();
            case 3:
                return new PingTask();
            default:
                throw new RuntimeException("messageTyp:" + messageType +    " is illegal");
        }
    }
}
