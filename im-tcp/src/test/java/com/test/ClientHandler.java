package com.test;

import com.example.model.Result;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;

public class ClientHandler extends SimpleChannelInboundHandler<Result> {
    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Result msg) throws Exception {
        LOGGER.info("result:{}",msg);
    }
}
