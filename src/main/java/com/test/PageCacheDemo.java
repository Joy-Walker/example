package com.test;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class PageCacheDemo {
    
    // 模拟写入1GB数据
    static final int TOTAL_SIZE = 1024 * 1024 * 1024; // 1GB
    static final int BUFFER_SIZE = 4096; // 4KB
    
    public static void main(String[] args) throws Exception {
        System.out.println("PageCache vs 普通写入对比\n");
        
        // 1. 普通写入（每次write都触发系统调用）
        long time1 = testNormalWrite();
        
        // 2. PageCache写入（内存映射）
        long time2 = testPageCacheWrite();
        
        System.out.printf("\n结果：PageCache快 %.1f 倍\n", (double)time1/time2);
        
        // 清理
        new File("normal.dat").delete();
        new File("mmap.dat").delete();
    }
    
    static long testNormalWrite() throws IOException {
        System.out.print("普通写入: ");
        long start = System.currentTimeMillis();
        
        // 每次write都是系统调用
        try (FileOutputStream fos = new FileOutputStream("normal.dat");
             BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE)) {
            
            byte[] data = new byte[BUFFER_SIZE];
            for (int i = 0; i < TOTAL_SIZE / BUFFER_SIZE; i++) {
                bos.write(data);  // 每次都是系统调用
            }
        }
        
        long time = System.currentTimeMillis() - start;
        System.out.println(time + "ms");
        return time;
    }
    
    static long testPageCacheWrite() throws IOException {
        System.out.print("PageCache: ");
        long start = System.currentTimeMillis();
        
        // 内存映射文件
        try (RandomAccessFile file = new RandomAccessFile("mmap.dat", "rw");
             FileChannel channel = file.getChannel()) {
            
            // 1. 一次性映射整个文件到内存
            MappedByteBuffer buffer = channel.map(
                FileChannel.MapMode.READ_WRITE, 0, TOTAL_SIZE);
            
            // 2. 直接操作内存（不进PageCache）
            byte[] data = new byte[BUFFER_SIZE];
            for (int i = 0; i < TOTAL_SIZE / BUFFER_SIZE; i++) {
                buffer.put(data);  // 纯内存操作
            }
            
            // 3. ★★★ 最后一次性刷盘 ★★★
            buffer.force();  // 把PageCache刷入磁盘
        }
        
        long time = System.currentTimeMillis() - start;
        System.out.println(time + "ms");
        return time;
    }
}