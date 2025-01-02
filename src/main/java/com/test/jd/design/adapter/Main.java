package com.test.jd.design.adapter;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-16 10:56:00
 */

/**
 * 当支付宝支付接口发生变更时，影响的只限于支付宝 Adapter；
 * 当要增加一个新的支付方式时，只需要再写一个新的 Adapter。
 */
public class Main {

    public static void main(String[] args) {

        PayService payService = new AliPayAdapter(new AliPay());

        payService.pay(1L);
    }
}
