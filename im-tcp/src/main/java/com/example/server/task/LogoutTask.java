package com.example.server.task;

import com.example.message.Message;
import com.example.pack.LogoutPack;
import com.example.session.SessionManager;
import com.example.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Objects;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:53:00
 */
public class LogoutTask implements Task {

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        LogoutPack logoutPack = JsonUtils.fromJson(new String(message.getBody()), LogoutPack.class);

        SessionManager.removeSession(logoutPack.getUserId());

        Objects.requireNonNull(ctx.channel().attr(AttributeKey.valueOf("userId")));

        ctx.channel().close();
    }
}
