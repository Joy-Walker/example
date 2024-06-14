package com.example.server.codec;

import com.example.model.Result;
import com.example.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-13 20:03:00
 */
public class NettyEncode extends MessageToByteEncoder<Result> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Result result, ByteBuf out) throws Exception {
        byte[] bytes = JsonUtils.toJson(result).getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
