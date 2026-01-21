package com.java.dserialization;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EventWithFormat {
    public String name;

    /**
     * 默认格式@JsonFormat 使用这种格式进行序列化
     */
    @JsonFormat(
      shape = JsonFormat.Shape.STRING,
            timezone = "GMT+8",
      pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;

    public EventWithFormat() {}

    public EventWithFormat(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "EventWithFormat{" +
                "name='" + name + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}