package com.example.session;

import io.netty.channel.Channel;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:31:00
 */
public class SessionManager {

    private static Map<String, Channel> sessionMap = new ConcurrentHashMap<>(64);

    public static void addSession(String sessionId, Channel channel) {
        sessionMap.put(sessionId, channel);
    }

    public static Channel getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

}
