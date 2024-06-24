package com.example.server.task;

import com.example.message.Message;
import com.example.model.Result;
import com.example.pack.LoginPack;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.session.connect.ConnectionHolder;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:29:00
 */
public class LoginTask implements Task{

    static Logger logger = LoggerFactory.getLogger(LoginTask.class);

    private final LoginService loginService;

    public LoginTask() {
        loginService = SpringContext.getBean(LoginService.class);
    }


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
        LoginPack loginPack = JsonUtils.fromJsonByte(message.getBody(), LoginPack.class);
        logger.info("loginPack:{}",loginPack);
        loginPack.setMessageType(message.getHeader().getMessageType());
        Result result = loginService.login(loginPack,ctx);
        ctx.writeAndFlush(result);
    }
}
