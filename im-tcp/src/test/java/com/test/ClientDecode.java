package com.test;

import com.example.model.Result;
import com.example.pack.LoginPack;
import com.example.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-13 20:21:00
 */
public class ClientDecode extends ByteToMessageDecoder {
    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientDecode.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] body = new byte[in.readableBytes()];
        in.readBytes(body, 0, body.length);
        Result result = JsonUtils.fromJsonByte(body, Result.class);
        out.add(result);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("连接建立成功....");

        new Thread(() -> {
           while (true) {
               ByteBuf buf = ctx.alloc().buffer();
               LoginPack loginPack = new LoginPack();
               loginPack.setPassword("***");
               loginPack.setFormId(1L);
               loginPack.setUserId("1");
               byte[] body = JsonUtils.toJson(loginPack).getBytes(StandardCharsets.UTF_8);
               buf.writeInt(24 + body.length); // totalLength
               buf.writeInt(0x12345678); // magicNumber
               buf.writeInt(1); // messageType
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
        }).start();
    }
}
