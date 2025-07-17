package com.example.pack;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-12 20:13:00
 */
public class P2PPack extends Basepack {

    private String content;

    private long sequenceId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(long sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public String toString() {
        return "P2PPack{" +
                "content='" + content + '\'' +
                ", sequenceId=" + sequenceId +
                '}';
    }
}
