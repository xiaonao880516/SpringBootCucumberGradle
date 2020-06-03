package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:33
 * @version 1.0
 */
public class ProvinceOperationCenterVerifyData {
    private int salesSum = 0;  //省运营中心销售额

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
