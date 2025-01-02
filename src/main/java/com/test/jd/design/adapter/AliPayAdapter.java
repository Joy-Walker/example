package com.test.jd.design.adapter;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-16 10:54:00
 */
public class AliPayAdapter implements PayService {

    private AliPay aliPay;

    public AliPayAdapter(AliPay aliPay) {
        this.aliPay = aliPay;
    }

    @Override
    public boolean pay(long amount) {
        aliPay.pay(amount);
        return true;
    }
}
