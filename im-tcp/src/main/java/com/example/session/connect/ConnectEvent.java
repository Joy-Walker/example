package com.example.session.connect;

import com.example.pack.Basepack;
import io.netty.channel.Channel;

public class ConnectEvent {


    private Channel channel;

    private Basepack basepack; ;

    private ConnectEventEnum event;

    public ConnectEvent(Channel channel, Basepack basepack, ConnectEventEnum event) {
        this.channel = channel;
        this.basepack = basepack;
        this.event = event;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Basepack getBasepack() {
        return basepack;
    }

    public void setBasepack(Basepack basepack) {
        this.basepack = basepack;
    }

    public ConnectEventEnum getEvent() {
        return event;
    }

    public void setEvent(ConnectEventEnum event) {
        this.event = event;
    }



}
