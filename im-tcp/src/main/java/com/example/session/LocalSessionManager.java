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
public class LocalSessionManager extends AbstractSessionManager{

    private final static Map<String, LoginPack> SESSION_MAP = new ConcurrentHashMap<>(64);
    @Override
    public void doAddSession(String sessionId, LoginPack loginPack) {
        SESSION_MAP.put(sessionId, loginPack);
    }
    @Override
    public LoginPack getSession(String sessionId) {
        return SESSION_MAP.get(sessionId);
    }

    @Override
    public void doRemoveSession(String sessionId) {
        SESSION_MAP.remove(sessionId);
    }

}
