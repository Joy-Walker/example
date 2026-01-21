package com.example.utils;

import com.example.model.Result;

public class AckUtils {

    public static Result ack(String messageKey) {
        return Result.success(messageKey);
    }

    public static Result fail(String messageKey ,String msg) {
        return Result.fail(msg,messageKey);
    }
}
