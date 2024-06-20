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
public class LocalSessionRegistry implements SessionRegistry{

    private final static Map<String, String> SESSION_MAP = new ConcurrentHashMap<>(64);
    @Override
    public void register(String sessionId, String topic) {
        SESSION_MAP.put(sessionId, topic);
    }
    @Override
    public String getTopic(String sessionId) {
        return SESSION_MAP.get(sessionId);
    }

    @Override
    public void remove(String sessionId) {
        SESSION_MAP.remove(sessionId);
    }

}
