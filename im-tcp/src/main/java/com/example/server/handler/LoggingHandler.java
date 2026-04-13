package com.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHandler extends ChannelDuplexHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            logMessage(ctx, "RECEIVED", msg);
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, io.netty.channel.ChannelPromise promise) throws Exception {
        if (msg instanceof ByteBuf) {
            logMessage(ctx, "SENDING", msg);
        }
        ctx.write(msg, promise);
    }

    /**
     * 默认是大端模式
     * 地址： 00  01  02   03
     * 数据： 00  00  00   85
     * 16进制： 0x00000085
     * @param ctx
     * @param eventName
     * @param msg
     */

    private void logMessage(ChannelHandlerContext ctx, String eventName, Object msg) {
        if (logger.isDebugEnabled()) {
            StringBuilder buf = new StringBuilder(eventName.length() + 2 + 10 + 1);
            buf.append(eventName).append(": ");
            ByteBuf byteBuf = (ByteBuf) msg;
            buf.append(byteBuf.readableBytes()).append("B");
            buf.append(StringUtil.NEWLINE);
            buf.append(ByteBufUtil.prettyHexDump(byteBuf));
            logger.debug("{}", buf);
        }
    }
}
