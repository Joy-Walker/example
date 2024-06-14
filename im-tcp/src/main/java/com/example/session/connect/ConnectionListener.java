package com.example.session.connect;

@FunctionalInterface
public interface ConnectionListener {

    public void onEvent(ConnectEvent event);
}
