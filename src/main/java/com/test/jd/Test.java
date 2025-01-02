package com.test.jd;


import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-09 23:01:00
 */
public class Test<T> {

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.purchaseName");
        Order order = new Order();
        order.setPurchaseName("张三");
        System.out.println(expression.getValue(order));
        System.out.println("hello\\world".contains("\\"));
        Test.class.getEnumConstants();


    }


    static class Order  {

        private String purchaseName;

        public void setPurchaseName(String purchaseName) {
            this.purchaseName = purchaseName;
        }

        public String getPurchaseName() {
            return purchaseName;
        }
    }




}




