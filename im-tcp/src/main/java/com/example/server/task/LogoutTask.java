package com.example.server.task;

import com.example.message.Message;
import com.example.pack.LogoutPack;
import com.example.session.connect.ConnectionHolder;
import com.example.utils.JsonUtils;
import com.example.utils.SpringContext;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 19:53:00
 */
public class LogoutTask implements Task {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogoutTask.class);

    private final ConnectionHolder connectionHolder;

    public LogoutTask() {
        connectionHolder = SpringContext.getBean(ConnectionHolder.class);
    }

    @Override
    public void run(ChannelHandlerContext ctx, Message message) {
        LogoutPack logoutPack = JsonUtils.fromJsonByte(message.getBody(), LogoutPack.class);
        connectionHolder.removeConnection(logoutPack);
    }
}
