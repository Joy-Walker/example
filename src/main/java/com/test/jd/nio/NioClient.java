package com.test.jd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-09 22:33:00
 */
public class NioClient {

    static String s = "hello world1111222";

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();

        channel.connect(new InetSocketAddress(9090));

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024000);
        int bodyLength = s.getBytes(StandardCharsets.UTF_8).length;

        for(int i = 0; i < 1; i++) {
            buffer.putInt(24 + bodyLength); //  总长度,解码后会被跳过
            buffer.putInt(0); // 魔数
            buffer.putInt(1); // type
            buffer.putInt(2); // version
            buffer.putInt(3); // 序列化类型
            buffer.putInt(bodyLength); // body 长度
            buffer.putInt(bodyLength + 16); //  总长度
            buffer.put(s.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
        }
        System.out.println("wating");
        Thread.currentThread().join();

    }
}
