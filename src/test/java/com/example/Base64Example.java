package com.example;

import java.util.Base64;

public class Base64Example {
    public static void main(String[] args) {
        // 要编码的字符串
        String originalString = "0";

        // 进行 Base64 编码
        String encodedString = Base64.getEncoder().encodeToString(new byte[]{3});
        System.out.println("Encoded string: " + encodedString);

        // 进行 Base64 解码
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("Decoded string: " + decodedString);
    }
}
