package com.example.netty.server.task;

import com.example.init.Init;
import com.example.message.Message;
import com.example.pack.LoginPack;
import com.example.session.SessionManager;
import com.example.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:29:00
 */
public class LoginTask implements Task{

    static Logger logger = LoggerFactory.getLogger(LoginTask.class);

    /**
     * TODO redis session
     *                           ios     user
     * appId:session:sessionId   android user
     *                           pc      user
     * @param ctx
     * @param message
     */

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        LoginPack loginPack = JsonUtils.fromJson(new String(message.getBody()), LoginPack.class);
        logger.info("loginPack:{}",loginPack);
        ctx.channel().attr(AttributeKey.valueOf("userId")).set(loginPack);
        SessionManager.addSession(loginPack.getUserId(),ctx.channel());
    }
}
