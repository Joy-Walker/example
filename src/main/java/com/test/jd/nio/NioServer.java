package com.test.jd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-09 22:06:00
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8888));
        ByteBuffer buffer = ByteBuffer.allocate(4);
        Selector selector = Selector.open();
        serverChannel.configureBlocking(false);
        SelectionKey selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(selectionKey);
        
        
        
        while (true) {
            int keys = selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                System.out.println(key);
                if(key.isAcceptable()) {
                    System.out.println("连接事件");
                    serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    SelectionKey key1 = channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("key1" + key1);
                }
                if(key.isReadable()) {
                    System.out.println("读事件");
                    SocketChannel channel = (SocketChannel) key.channel();
                    int len = channel.read(buffer);
                    if(len == -1) {
                        key.cancel();
                    }else{
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0, buffer.limit()));

                    }
                }
            }
        }
    }
}
