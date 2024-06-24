package com.example.service.impl;

import com.example.model.Result;
import com.example.pack.LoginPack;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.session.connect.ConnectionHolder;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-24 19:43:00
 */
@Service
public class LoginServiceImpl implements LoginService {

    static  Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionHolder connectionHolder ;

    @Override
    public Result login(LoginPack loginPack, ChannelHandlerContext ctx) {
        int exists = userService.getUserByUserIdAndPassword(loginPack.getUserId(), loginPack.getPassword());
        if(exists <= 0) {
            logger.error("用户名或者密码错误, userId: {}" , loginPack.getUserId());
            return Result.fail(501, "用户名或者密码错误");
        }
        connectionHolder.addConnection(loginPack,ctx.channel());
        return Result.success("登录成功");
    }
}
