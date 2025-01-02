package com.test.jd.file;

import java.io.FileInputStream;
import java.io.IOException;

public class PageCacheDemo {
    public static void main(String[] args) throws InterruptedException {
        String filePath = "/Users/apple/Downloads/第9章消息业务的流程之打通消息收发核心.zip"; // 替换为你要读取的大文件路径
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[65536]; // 64KB缓冲区，可以根据实际情况调整大小
            long startTime = System.currentTimeMillis();
            
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                Thread.sleep(20);
                System.out.println("读取到" + bytesRead + "字节的数据");
            }
            
            long endTime = System.currentTimeMillis();
            System.out.println("文件读取完成，耗时：" + (endTime - startTime) + " 毫秒");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("读取文件时发生错误：" + e.getMessage());
        }
        Thread.sleep(20000);
    }
}
