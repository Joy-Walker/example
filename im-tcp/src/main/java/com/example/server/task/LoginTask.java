package com.example.server.task;

import com.example.message.Message;
import com.example.pack.LoginPack;
import com.example.session.ConnectionHolder;
import com.example.session.SessionManager;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.constant.Constants.LOGIN_USER;

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
        // attr是channel的属性，每个client都有自己的channel因此是线程安全的
        ctx.channel().attr(LOGIN_USER).set(loginPack);
        ConnectionHolder.addConnection(loginPack.getUserId(),ctx.channel());

        // TOTO 创建UserSession对象存redis
        SpringContext.getBean(SessionManager.class).addSession(loginPack.getUserId(),loginPack);

    }
}
