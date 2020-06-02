package com.youxinger.springbootcucumbergradle.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:16
 * @version 1.0
 */
public class OperationCenter {

    private String name;  // 运营中心名称
    private String number;  //运营中心编号
    private List<Store> storeList = new ArrayList<>();
    private OperationCenterVerifyData preVerifyData;
    private OperationCenterVerifyData postVerifyData;
    private OperationCenterVerifyData expectedData;

    public OperationCenter(String name, String number){
        this.name = name;
        this.number = number;
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

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public OperationCenterVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(OperationCenterVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public OperationCenterVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(OperationCenterVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public OperationCenterVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(OperationCenterVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "OperationCenter{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", storeList=" + storeList +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}

class OperationCenterVerifyData {
    private int salesSum = 0;  //运营中心销售额

    public int getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(int salesSum) {
        this.salesSum = salesSum;
    }

    @Override
    public String toString() {
        return "ProvinceOperationCenterVerifyData{" +
                "salesSum=" + salesSum +
                '}';
    }
}
