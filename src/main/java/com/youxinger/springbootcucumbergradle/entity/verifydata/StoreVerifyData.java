package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:35
 * @version 1.0
 */
public class StoreVerifyData {
    private int arrivedStoreNum = 0;  //门店到店次数
    private float measurementOrderRate = 0.0f;  //门店测量成单率
    private int newVIPNum = 0;  //门店新增会员数
    private float newVIPOrderRate = 0.0f;  //门店新会员成单率
    private int orderCount = 0;  //门店订单数
    private int refundCount = 0;  //门店退单数
    private float salesSum = 0.0f;  //门店销售总额
    private float salesRatio = 0.0f;  //门店销售完成比
    private int platSalesSum = 0;  // 门店平台销售总额
    private float platSalesRatio = 0.0f;  //门店平台销售业绩占比

    public int getArrivedStoreNum() {
        return arrivedStoreNum;
    }

    public void setArrivedStoreNum(int arrivedStoreNum) {
        this.arrivedStoreNum = arrivedStoreNum;
    }

    public float getMeasurementOrderRate() {
        return measurementOrderRate;
    }

    public void setMeasurementOrderRate(float measurementOrderRate) {
        this.measurementOrderRate = measurementOrderRate;
    }

    public int getNewVIPNum() {
        return newVIPNum;
    }

    public void setNewVIPNum(int newVIPNum) {
        this.newVIPNum = newVIPNum;
    }

    public float getNewVIPOrderRate() {
        return newVIPOrderRate;
    }

    public void setNewVIPOrderRate(float newVIPOrderRate) {
        this.newVIPOrderRate = newVIPOrderRate;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public float getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(float salesSum) {
        this.salesSum = salesSum;
    }

    public float getSalesRatio() {
        return salesRatio;
    }

    public void setSalesRatio(float salesRatio) {
        this.salesRatio = salesRatio;
    }

    public int getPlatSalesSum() {
        return platSalesSum;
    }

    public void setPlatSalesSum(int platSalesSum) {
        this.platSalesSum = platSalesSum;
    }

    public float getPlatSalesRatio() {
        return platSalesRatio;
    }

    public void setPlatSalesRatio(float platSalesRatio) {
        this.platSalesRatio = platSalesRatio;
    }

    @Override
    public String toString() {
        return "StoreVerifyData{" +
                "arrivedStoreNum=" + arrivedStoreNum +
                ", measurementOrderRate=" + measurementOrderRate +
                ", newVIPNum=" + newVIPNum +
                ", newVIPOrderRate=" + newVIPOrderRate +
                ", orderCount=" + orderCount +
                ", refundCount=" + refundCount +
                ", salesSum=" + salesSum +
                ", salesRatio=" + salesRatio +
                ", platSalesSum=" + platSalesSum +
                ", platSalesRatio=" + platSalesRatio +
                '}';
    }
}