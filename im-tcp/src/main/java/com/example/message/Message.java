package com.example.message;


/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 14:16:00
 */
public class Message {

    private MessageHeader header;

    private byte[] body;

    public Message() {
    }

    public Message(MessageHeader header, byte[] body) {
        this.header = header;
        this.body = body;
    }


    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", body=" + new String(body) +
                '}';
    }
}
