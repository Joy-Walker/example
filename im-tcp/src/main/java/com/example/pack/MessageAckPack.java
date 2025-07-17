package com.example.pack;

/**
 * @author :panligang
 * @description :
 * @create :2025-07-16 17:21:00
 */
public class MessageAckPack extends Basepack {

    private Long lastMessageId;

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }
}
