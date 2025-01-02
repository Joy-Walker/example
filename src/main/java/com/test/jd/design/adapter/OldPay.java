package com.test.jd.design.adapter;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-16 10:51:00
 */

/**
 * 旧的支付方式已经存在，之前没有抽象接口
 *
 */
public class OldPay {

    void pay(long amount) throws RuntimeException {
       if(amount <= 0) {
           throw new RuntimeException("参数不合法");
       }

        System.out.println("支付成功，使用OldPay ");
    }
}
