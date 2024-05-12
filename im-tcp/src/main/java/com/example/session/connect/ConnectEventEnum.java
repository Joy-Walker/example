package com.example.session.connect;

public enum ConnectEventEnum {


    CONNECT("connect"),

    DIS_CONNECT("dis_connect");

    private String event;

    ConnectEventEnum(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
