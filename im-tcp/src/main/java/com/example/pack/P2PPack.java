package com.example.pack;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-12 20:13:00
 */
public class P2PPack extends Basepack {

    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "P2PPack{" +
                "userId='" + userId + '\'' +
                ", formId=" + formId +
                ", toId=" + toId +
                ", messageType=" + messageType +
                ", messageKey='" + messageKey + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
