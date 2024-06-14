package com.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 创建EventLoopGroup，可以理解为是一个线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建Bootstrap实例，用于客户端的启动配置
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup) // 设置线程组
                    .channel(NioSocketChannel.class) // 指定使用NIO的通信模式
                    .handler(new ChannelInitializer<SocketChannel>() { // 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 添加StringEncoder处理器，用于将字符串转换为ByteBuf以便网络传输
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,0,4,0,4))
                                    .addLast(new ClientHandler());
                        }
                    });

            System.out.println("Netty Client is ready...");

            // 连接到远程主机，这里以localhost和8080端口为例
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
            // 等待连接关闭的异步操作完成
            future.channel().closeFuture().sync();
        } finally {
            // 优雅地关闭EventLoopGroup，释放资源
            workerGroup.shutdownGracefully();
        }
    }
}