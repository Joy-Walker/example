package com.test.jd.enums;

import java.util.function.Predicate;

public interface EventHandler<E> {

    /**
     * 处理事件，要求事件不能阻塞
     *
     * @param event
     */
    void handle(E event);

    /**
     * 包装器
     *
     * @param predicate
     * @return
     */
    default EventHandler<E> wrap(final Predicate<E> predicate) {
        return e -> {
            if (predicate == null || predicate.test(e)) {
                handle(e);
            }
        };
    }




    public static void main(String[] args) {
        EventHandler handler = event -> System.out.println(123);

        EventHandler wrap = handler.wrap(a -> args == null);
        wrap.handle(123);
    }

}