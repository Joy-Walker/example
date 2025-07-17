package com.example.pack;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:19:00
 */
public class LoginPack extends Basepack{

    private String password;

    private Long lastMessageId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    @Override
    public String toString() {
        return "LoginPack{" +
                "password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", formId=" + formId +
                ", toId=" + toId +
                ", messageType=" + messageType +
                '}';
    }
}
