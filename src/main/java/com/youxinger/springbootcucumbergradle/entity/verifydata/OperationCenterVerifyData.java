package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:32
 * @version 1.0
 */
public class OperationCenterVerifyData {
    private float salesSum = 0;  //运营中心销售额

    public float getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(float salesSum) {
        this.salesSum = salesSum;
    }

    @Override
    public String toString() {
        return "ProvinceOperationCenterVerifyData{" +
                "salesSum=" + salesSum +
                '}';
    }
}