package com.example.server.handler;

import com.example.message.Message;
import com.example.model.Result;
import com.example.server.task.Task;
import com.example.server.task.TaskFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 14:51:00
 */
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Message> {


    public static final ServerHandler INSTANCE = new ServerHandler();

    private ServerHandler(){}

    public static ServerHandler getInstance(){
        return INSTANCE;
    }

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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("exceptionCaught",cause);
        ctx.writeAndFlush(Result.fail(cause.getMessage()));
    }
}
