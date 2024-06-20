package com.example.session.connect;

@FunctionalInterface
public interface ConnectionListener {

     void onEvent(ConnectEvent event);
}
