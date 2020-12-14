package com.youxinger.springbootcucumbergradle.bean;

/**
 * @author mengwei
 * 2020/7/2 11:02
 * @version 1.0
 * 创建订单，服务器返回订单信息
 */
public class OrderInfo {

    //订单id
    private String orderId;

    //实际要支付的金额
    private String realPay;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRealPay() {
        return realPay;
    }

    public void setRealPay(String realPay) {
        this.realPay = realPay;
    }
}
