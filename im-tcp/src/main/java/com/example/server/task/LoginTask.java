package com.example.server.task;

import com.example.message.Message;
import com.example.model.Result;
import com.example.pack.LoginPack;
import com.example.service.UserService;
import com.example.session.connect.ConnectionHolder;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.constant.Constants.LOGIN_USER;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-10 19:29:00
 */
public class LoginTask implements Task{

    private final UserService userService;


    private final ConnectionHolder connectionHolder;


    public LoginTask() {
        userService = SpringContext.getBean(UserService.class);
        connectionHolder = SpringContext.getBean(ConnectionHolder.class);
    }

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
        LoginPack loginPack = JsonUtils.fromJsonByte(message.getBody(), LoginPack.class);
        logger.info("loginPack:{}",loginPack);
        loginPack.setMessageType(message.getHeader().getMessageType());
        int exists = userService.getUserByUserIdAndPassword(loginPack.getUserId(), loginPack.getPassword());
        if(exists <= 0) {
            logger.error("用户名或者密码错误, userId: {}" , loginPack.getUserId());
            ctx.writeAndFlush(Result.fail(501, "用户名或者密码错误"));
            return;
        }
        connectionHolder.addConnection(loginPack,ctx.channel());
        ctx.writeAndFlush(Result.success("登录成功!"));
    }
}
