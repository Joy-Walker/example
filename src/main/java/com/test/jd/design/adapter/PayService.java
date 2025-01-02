package com.test.jd.design.adapter;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-16 10:50:00
 */

/**
 * 支付接口
 * 这块最重要的是针对参数的抽象，使得可以同时满足新旧接口
 *
 */
public interface PayService {


    boolean pay(long amount);
}
