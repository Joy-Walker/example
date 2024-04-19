package com.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-17 20:00:00
 */
public class HearBeatHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(HearBeatHandler.class);


    /**
     * 下线逻辑和主动退出不一样
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)  {

        IdleStateEvent event = (IdleStateEvent) evt;
        if(event.state() == IdleState.ALL_IDLE){
            Long lastTime = (Long) ctx.channel().attr(AttributeKey.valueOf("lastTime")).get();
            if(lastTime == null){
                ctx.channel().attr(AttributeKey.valueOf("lastTime")).set(System.currentTimeMillis());
                logger.info("客户端：{} 掉线了",ctx.channel().remoteAddress());
                ctx.close();
            }else{
                if(System.currentTimeMillis() - lastTime > 10000){
                    ctx.channel().attr(AttributeKey.valueOf("lastTime")).set(System.currentTimeMillis());
                    logger.info("客户端：{} 掉线了",ctx.channel().remoteAddress());
                }
            }
        }
    }
}
