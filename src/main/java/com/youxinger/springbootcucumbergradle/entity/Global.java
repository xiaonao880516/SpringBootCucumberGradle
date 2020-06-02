package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:18
 * @version 1.0
 */
public class Global {

    //所有省运营中心
    private List<ProvinceOperationCenter> provinceOperationCenterList = new ArrayList<>();
    //总仓
    private Repository repository;
    private GlobalVerifyData preVerifyData;
    private GlobalVerifyData postVerifyData;
    private GlobalVerifyData expectedData;

    public Global(){
        this.repository = new Repository("总仓", Constants.PRODUCTS_BARCODE);
    }

    public List<ProvinceOperationCenter> getProvinceOperationCenterList() {
        return provinceOperationCenterList;
    }

    public void setProvinceOperationCenterList(List<ProvinceOperationCenter> provinceOperationCenterList) {
        this.provinceOperationCenterList = provinceOperationCenterList;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public GlobalVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(GlobalVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public GlobalVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(GlobalVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public GlobalVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(GlobalVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "Global{" +
                "provinceOperationCenterList=" + provinceOperationCenterList +
                ", repository=" + repository +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}

class GlobalVerifyData {
    private int arrivedStoreNum = 0;  //总揽到店次数
    private float measurementOrderRate = 0.0f;  //总揽测量成单率
    private int newVIPNum = 0;  //总揽新增会员数
    private float newVIPOrderRate = 0.0f; //总揽新会员成单率
    private int orderCount = 0;  //总揽订单数
    private int refundCount = 0;  // 总揽退单数
    private float salesSum = 0.0f;  // 总揽销售总额
    private float salesRatio = 0.0f;  //总揽销售完成比

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

    @Override
    public String toString() {
        return "GlobalVerifyData{" +
                "arrivedStoreNum=" + arrivedStoreNum +
                ", measurementOrderRate=" + measurementOrderRate +
                ", newVIPNum=" + newVIPNum +
                ", newVIPOrderRate=" + newVIPOrderRate +
                ", orderCount=" + orderCount +
                ", refundCount=" + refundCount +
                ", salesSum=" + salesSum +
                ", salesRatio=" + salesRatio +
                '}';
    }
}
