package com.test;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class SimplePageCacheTest {
    public static void main(String[] args) throws Exception {
        System.out.println("PageCache vs 普通文件写入对比\n");
        
        // 测试1GB文件写入
        int size = 1024 * 1024 * 1024; // 1GB
        
        // 1. 普通文件写入
        long start1 = System.currentTimeMillis();
        normalWrite("normal.dat", size);
        long end1 = System.currentTimeMillis();
        
        // 2. PageCache写入
        long start2 = System.currentTimeMillis();
        mmapWrite("mmap.dat", size);
        long end2 = System.currentTimeMillis();
        
        System.out.println("\n结果对比:");
        System.out.printf("普通写入: %d ms\n", end1 - start1);
        System.out.printf("PageCache: %d ms\n", end2 - start2);
        System.out.printf("速度提升: %.1f倍\n", (double)(end1 - start1)/(end2 - start2));
        
        // 清理文件
        new File("normal.dat").delete();
        new File("mmap.dat").delete();
    }
    
    // 普通文件写入
    static void normalWrite(String filename, int size) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw");
             FileChannel channel = file.getChannel()) {
            
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            byte[] data = new byte[4096];
            
            for (int i = 0; i < size; i += 4096) {
                buffer.clear();
                buffer.put(data);
                buffer.flip();
                channel.write(buffer);
            }
            channel.force(true); // 刷盘
        }
    }
    
    // PageCache写入（内存映射文件）
    static void mmapWrite(String filename, int size) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw");
             FileChannel channel = file.getChannel()) {
            
            // 创建内存映射文件
            MappedByteBuffer buffer = channel.map(
                FileChannel.MapMode.READ_WRITE, 0, size);
            
            byte[] data = new byte[4096];
            
            // 直接写入内存映射缓冲区
            for (int i = 0; i < size; i += 4096) {
                buffer.put(data);
            }
            buffer.force(); // 刷盘
        }
    }
}