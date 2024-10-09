package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2024-07-01 18:45:00
 */
public class MemoryTest {

    public static List<Object> obj = new ArrayList<>();

    public static void main(String[] args) {
        obj.add(new byte[1024]);
    }
}
