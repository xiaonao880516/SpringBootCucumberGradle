package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:17
 * @version 1.0
 */
public class Store {

    private String name;//门店名称
    private String number;//门店编号
    private Repository repository;//门店仓库
    private List<Platform> platformList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private StoreVerifyData preVerifyData;
    private StoreVerifyData postVerifyData;
    private StoreVerifyData expectedData;

    public Store(String name, String number){
        this.name = name;
        this.number = number;
        this.repository = new Repository(name, Constants.PRODUCTS_BARCODE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public StoreVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(StoreVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public StoreVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(StoreVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public StoreVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(StoreVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", repository=" + repository +
                ", platformList=" + platformList +
                ", employeeList=" + employeeList +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}

class StoreVerifyData {
    private int arrivedStoreNum = 0;  //门店到店次数
    private float measurementOrderRate = 0.0f;  //门店测量成单率
    private int newVIPNum = 0;  //门店新增会员数
    private float newVIPOrderRate = 0.0f;  //门店新会员成单率
    private int orderCount = 0;  //门店订单数
    private int refundCount = 0;  //门店退单数
    private int salesSum = 0;  //门店销售总额
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

    public int getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(int salesSum) {
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
