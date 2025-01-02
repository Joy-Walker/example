package com.test;

import jdk.nashorn.internal.runtime.options.Option;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :panligang
 * @description :
 * @create :2024-07-01 18:45:00
 */
public class MemoryTest {

    public static volatile Map<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
       new Thread(()->{
           map.forEach((k,v)->{
               // todo 业务逻辑
           });
       }).start();


//        new Thread(()->{
//            map.put("key","value");
//            //更新map
//        }).start();

        new Thread(()->{
            Map<String,String> temp = new ConcurrentHashMap<>();
            temp.put("key","value");
            map = temp;
            temp = null;
        }).start();
    }
}
