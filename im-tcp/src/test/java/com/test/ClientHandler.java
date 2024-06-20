package com.test;

import com.example.constant.Constants;
import com.example.model.Result;
import com.example.pack.P2PPack;
import com.example.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ClientHandler extends SimpleChannelInboundHandler<Result> {
    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Result msg) throws Exception {
        LOGGER.info("result:{}",msg);
        ByteBuf buf = ctx.alloc().buffer();
        P2PPack p2PPack = new P2PPack();
        p2PPack.setFormId(1L);
        p2PPack.setToId(1L);
        p2PPack.setUserId("1");
        p2PPack.setContent("你好!!!");
        p2PPack.setMessageKey(UUID.randomUUID().toString());
        byte[] body = JsonUtils.toJson(p2PPack).getBytes(StandardCharsets.UTF_8);
        buf.writeInt(24 + body.length); // totalLength
        buf.writeInt(0x12345678); // magicNumber
        buf.writeInt(Constants.MessageType.P2P); // messageType
        buf.writeInt(1); // version
        buf.writeInt(1); // serializerType
        buf.writeInt(body.length); // bodyLength
        buf.writeInt(28 + body.length);
        buf.writeBytes(body);
        ctx.writeAndFlush(buf);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
