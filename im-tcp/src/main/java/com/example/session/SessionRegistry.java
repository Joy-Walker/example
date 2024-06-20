package com.example.session;

import com.example.pack.LoginPack;
import io.netty.channel.Channel;




/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:31:00
 */
public interface SessionRegistry {

     void register(String userId, String topic);

    String getTopic(String sessionId);

      void remove(String sessionId);

}
