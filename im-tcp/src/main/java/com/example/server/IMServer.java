package com.example.server;

import com.example.server.codec.NettyCodec;
import com.example.server.handler.HearBeatHandler;
import com.example.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * sdk --- 接入层 --- 逻辑层 --- 存储层
 *
 * 接入层 发送mq消息
 * 逻辑层 订阅mq消息 好友关系校验 回复ack到mq
 * 接入层订阅mq消息，回复sdk ack
 *
 *
 * 逻辑层 处理消息
 * @description:
 * @author: lld
 * @version: 1.0
 */

public class IMServer {

    private int port;

    /**
     * 机器标识
     */
    private String brokerId;

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); //线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup();//

        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decode",new NettyCodec())
                                    .addLast("handler",new ServerHandler())
                                    .addLast(new IdleStateHandler(0,0,10))
                                    .addLast("hb",new HearBeatHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }
}
