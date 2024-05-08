package com.example.session;

import com.example.pack.LoginPack;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:31:00
 */
//@Component
public class LocalSessionManager implements SessionManager{

    private static Map<String, LoginPack> sessionMap = new ConcurrentHashMap<>(64);

    public void addSession(String sessionId, LoginPack loginPack) {
        sessionMap.put(sessionId, loginPack);
    }

    public LoginPack getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

}
