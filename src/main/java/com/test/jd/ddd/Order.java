package com.test.jd.ddd;

import java.util.Date;
import java.util.List;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-15 21:45:00
 */
public class Order {

    private Long id;

    private String userId;

    private String address;

    private String price;

    private Date createTime;

    private byte status;

    private List<OrderDetail> orderDetailList;


}
