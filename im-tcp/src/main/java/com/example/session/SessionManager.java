package com.example.session;

import com.example.pack.LoginPack;
import io.netty.channel.Channel;




/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:31:00
 */
public interface SessionManager {

     void addSession(String sessionId, LoginPack loginPack);

    LoginPack getSession(String sessionId);

      void removeSession(String sessionId);

}
