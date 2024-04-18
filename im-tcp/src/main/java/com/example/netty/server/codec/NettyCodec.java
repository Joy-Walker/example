package com.example.netty.server.codec;

import com.example.message.Message;
import com.example.message.MessageHeader;
import io.netty.buffer.ByteBuf;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;



/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 20:35:00
 */
public class NettyCodec extends LengthFieldBasedFrameDecoder {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NettyCodec.class);

    /**
     * Creates a new instance.
     *
     * @param maxFrameLength: 最大报文长度
     *        the maximum length of the frame.  If the length of the frame is
     *        greater than this value, {@link TooLongFrameException} will be
     *        thrown.
     * @param lengthFieldOffset：长度字段偏移量
     *        the offset of the length field
     * @param lengthFieldLength：长度字段长度
     *        the length of the length field
     * @param lengthAdjustment：长度字段调整
     *        the compensation value to add to the value of the length field
     * @param initialBytesToStrip：需要跳过的字节数
     *        the number of first bytes to strip out from the decoded frame
     */
    public NettyCodec() {
        super(Integer.MAX_VALUE, 0, 4, 0, 4);
    }

    /**
     *
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = null;
        try {
            frame = (ByteBuf) super.decode(ctx, in);
            if (null == frame) {
                return null;
            }
            String receivedDataAsHexString = ByteBufUtil.hexDump(frame);
            log.info("Received data as hex: {}", receivedDataAsHexString);
            MessageHeader messageHeader = decodeHeader(frame);
            Message message = decodeMessage(messageHeader, frame);
            return message;

        } catch (Exception e) {
            log.error("decode exception, " +  e);
            ctx.channel().close();
        } finally {
            if (null != frame) {
                frame.release();
            }
        }

        return null;
    }

    private Message decodeMessage(MessageHeader messageHeader, ByteBuf frame) {
        int bodyLength = messageHeader.getBodyLength();
        byte[] body = new byte[bodyLength];
        frame.readBytes(body);
        return new Message(messageHeader, body);
    }

    private MessageHeader decodeHeader(ByteBuf frame) {
        MessageHeader.Builder builder = new MessageHeader.Builder();
        int magic = frame.readInt();
        int messageTYpe = frame.readInt();
        int version = frame.readInt();
        int serializerType = frame.readInt();
        int bodyLength = frame.readInt();
        int length = frame.readInt();

        return builder.setMessageType(messageTYpe)
                .setMagicNumber(magic)
                .setVersion(version)
                .setSerializerType(serializerType)
                .setBodyLength(bodyLength)
                .setLength(length).build();

    }
}
