package com.test.jd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-16 18:37:00
 */
public class MutilThreadNioServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8888));
        serverChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()) {
                    System.out.println("连接事件");
                    serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    Work work = new Work();
                    work.registery(channel);
                }
            }
        }
    }

    static class Work implements Runnable{

        private Selector selector;

        private Thread thread;

        private volatile boolean start = false;

        private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        public Work() {
            try {
                if(!start) {
                    selector = Selector.open();
                    thread = new Thread(this);
                    thread.start();
                    start = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void registery(SocketChannel channel) {

            queue.offer(()->{
                try {
                    channel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(4));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":注册读事件");
            });

            selector.wakeup();


        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":开始执行");
                while (true){
                    selector.select();
                    Runnable run = queue.poll();
                    if(run != null) {
                        run.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        System.out.println(key);
                        if(key.isReadable()) {
                            System.out.println("读事件");
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            if(channel.read(buffer) == -1) {
                                key.cancel();
                            }
                            buffer.flip();
                            System.out.println(buffer.get(buffer.array(),buffer.position(),buffer.limit()));
                            buffer.compact();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
