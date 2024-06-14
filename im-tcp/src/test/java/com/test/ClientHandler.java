package com.test;

import com.example.message.MessageHeader;
import com.example.model.Result;
import com.example.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-13 20:21:00
 */
public class ClientHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBuf frame = null;
        int length = in.readableBytes();
        String json = in.toString(CharsetUtil.UTF_8);
        Result result = JsonUtils.fromJson(json, Result.class);
        out.add(result);
        in.release();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立了");
    }
}
