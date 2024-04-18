package com.example.netty.server.handler;

import com.example.message.Message;
import com.example.message.MessageHeader;
import com.example.netty.server.task.LoginTask;
import com.example.netty.server.task.Task;
import com.example.netty.server.task.TaskFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 14:51:00
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message> {

    static final Logger logger = org.slf4j.LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int magicNumber = msg.getHeader().getMagicNumber();
        if (magicNumber != 0x12345678){
            logger.warn("magicNumber is {},is not right.",magicNumber);
        }
        int messageType = msg.getHeader().getMessageType();
        Task task = TaskFactory.getTask(messageType);
        task.run(ctx,msg);
    }

}
