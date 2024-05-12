package com.example.session.connect;

import io.netty.channel.Channel;
@FunctionalInterface
public interface ConnectionListener {

    public void onEvent(ConnectEvent event);
}
