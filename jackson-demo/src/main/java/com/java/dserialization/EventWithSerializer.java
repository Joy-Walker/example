package com.java.dserialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class EventWithSerializer {
    public String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date eventDate;

    @Override
    public String toString() {
        return "EventWithSerializer{" +
                "name='" + name + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}