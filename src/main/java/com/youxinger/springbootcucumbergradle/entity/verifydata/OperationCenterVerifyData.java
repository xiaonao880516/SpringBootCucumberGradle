package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:32
 * @version 1.0
 */
public class OperationCenterVerifyData {
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