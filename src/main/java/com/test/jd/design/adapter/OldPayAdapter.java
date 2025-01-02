package com.test.jd.design.adapter;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-16 10:54:00
 */
public class OldPayAdapter implements PayService {

    private OldPay oldPay;

    public OldPayAdapter(OldPay oldPay) {
        this.oldPay = oldPay;
    }

    @Override
    public boolean pay(long amount) {
       try{
           oldPay.pay(amount);
       }catch (RuntimeException e) {
           return false;
       }
       return true;
    }
}
