package com.java.serialization;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class EventWithSerializer {
    public String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date eventDate;

    public EventWithSerializer(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
}