package com.example.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-20 13:12:00
 */
public class ConnectionHolder {

    private static Map<String, io.netty.channel.Channel> connectionMap = new ConcurrentHashMap<>();

    public static void addConnection(String userId, Channel channel) {
        connectionMap.put(userId, channel);
    }

    public static Channel getConnection(String userId) {
        return connectionMap.get(userId);
    }

    public static void removeConnection(String sessionId) {
        connectionMap.remove(sessionId);
    }
}
