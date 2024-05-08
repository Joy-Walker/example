package com.example.server.task;

import com.example.message.Message;
import com.example.pack.LogoutPack;
import com.example.session.SessionManager;
import com.example.utils.JsonUtils;
import com.example.utils.RemotingUtil;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Objects;

import static com.example.constant.Constants.LOGIN_USER;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:53:00
 */
public class LogoutTask implements Task {

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        LogoutPack logoutPack = JsonUtils.fromJson(new String(message.getBody()), LogoutPack.class);
        SpringContext.getBean(SessionManager.class).removeSession(logoutPack.getUserId());
        ctx.channel().attr(LOGIN_USER).set(null);
        RemotingUtil.closeChannel(ctx.channel());
    }
}
