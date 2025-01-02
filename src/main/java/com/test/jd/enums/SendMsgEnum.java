package com.test.jd.enums;

import java.util.function.Consumer;

/**
 * @author :panligang
 * @description :
 * @create :2023-04-27 10:59:00
 */
public enum SendMsgEnum {

    EMAIL("email"){
        @Override
        public void sendMsg(String msg, Consumer<String> consumer) {
            consumer.accept(msg);
        }
    },
    FS ("fs"){
        @Override
        public void sendMsg(String msg, Consumer<String> consumer) {
            consumer.accept(msg);
        }
    };

    private String type;

    SendMsgEnum(String type) {
        this.type = type;
    }

    public abstract void sendMsg(String msg, Consumer<String> consumer);

}


